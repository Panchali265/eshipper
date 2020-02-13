import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotificationCategory } from 'app/shared/model/notification-category.model';
import { NotificationCategoryService } from './notification-category.service';
import { NotificationCategoryDeleteDialogComponent } from './notification-category-delete-dialog.component';

@Component({
  selector: 'jhi-notification-category',
  templateUrl: './notification-category.component.html'
})
export class NotificationCategoryComponent implements OnInit, OnDestroy {
  notificationCategories?: INotificationCategory[];
  eventSubscriber?: Subscription;

  constructor(
    protected notificationCategoryService: NotificationCategoryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.notificationCategoryService
      .query()
      .subscribe((res: HttpResponse<INotificationCategory[]>) => (this.notificationCategories = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotificationCategories();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotificationCategory): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotificationCategories(): void {
    this.eventSubscriber = this.eventManager.subscribe('notificationCategoryListModification', () => this.loadAll());
  }

  delete(notificationCategory: INotificationCategory): void {
    const modalRef = this.modalService.open(NotificationCategoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notificationCategory = notificationCategory;
  }
}
