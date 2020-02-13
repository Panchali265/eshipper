import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotificationMethod } from 'app/shared/model/notification-method.model';
import { NotificationMethodService } from './notification-method.service';
import { NotificationMethodDeleteDialogComponent } from './notification-method-delete-dialog.component';

@Component({
  selector: 'jhi-notification-method',
  templateUrl: './notification-method.component.html'
})
export class NotificationMethodComponent implements OnInit, OnDestroy {
  notificationMethods?: INotificationMethod[];
  eventSubscriber?: Subscription;

  constructor(
    protected notificationMethodService: NotificationMethodService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.notificationMethodService
      .query()
      .subscribe((res: HttpResponse<INotificationMethod[]>) => (this.notificationMethods = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotificationMethods();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotificationMethod): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotificationMethods(): void {
    this.eventSubscriber = this.eventManager.subscribe('notificationMethodListModification', () => this.loadAll());
  }

  delete(notificationMethod: INotificationMethod): void {
    const modalRef = this.modalService.open(NotificationMethodDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notificationMethod = notificationMethod;
  }
}
