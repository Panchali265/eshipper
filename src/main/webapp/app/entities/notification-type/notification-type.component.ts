import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotificationType } from 'app/shared/model/notification-type.model';
import { NotificationTypeService } from './notification-type.service';
import { NotificationTypeDeleteDialogComponent } from './notification-type-delete-dialog.component';

@Component({
  selector: 'jhi-notification-type',
  templateUrl: './notification-type.component.html'
})
export class NotificationTypeComponent implements OnInit, OnDestroy {
  notificationTypes?: INotificationType[];
  eventSubscriber?: Subscription;

  constructor(
    protected notificationTypeService: NotificationTypeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.notificationTypeService.query().subscribe((res: HttpResponse<INotificationType[]>) => (this.notificationTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotificationTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotificationType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotificationTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('notificationTypeListModification', () => this.loadAll());
  }

  delete(notificationType: INotificationType): void {
    const modalRef = this.modalService.open(NotificationTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notificationType = notificationType;
  }
}
