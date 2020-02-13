import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWebNotification } from 'app/shared/model/web-notification.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WebNotificationService } from './web-notification.service';
import { WebNotificationDeleteDialogComponent } from './web-notification-delete-dialog.component';

@Component({
  selector: 'jhi-web-notification',
  templateUrl: './web-notification.component.html'
})
export class WebNotificationComponent implements OnInit, OnDestroy {
  webNotifications?: IWebNotification[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected webNotificationService: WebNotificationService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.webNotificationService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWebNotification[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInWebNotifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWebNotification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWebNotifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('webNotificationListModification', () => this.loadPage());
  }

  delete(webNotification: IWebNotification): void {
    const modalRef = this.modalService.open(WebNotificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.webNotification = webNotification;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWebNotification[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/web-notification'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.webNotifications = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
