import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotificationMethod } from 'app/shared/model/notification-method.model';
import { NotificationMethodService } from './notification-method.service';

@Component({
  templateUrl: './notification-method-delete-dialog.component.html'
})
export class NotificationMethodDeleteDialogComponent {
  notificationMethod?: INotificationMethod;

  constructor(
    protected notificationMethodService: NotificationMethodService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notificationMethodService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notificationMethodListModification');
      this.activeModal.close();
    });
  }
}
