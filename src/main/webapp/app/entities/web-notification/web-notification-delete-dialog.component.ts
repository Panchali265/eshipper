import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWebNotification } from 'app/shared/model/web-notification.model';
import { WebNotificationService } from './web-notification.service';

@Component({
  templateUrl: './web-notification-delete-dialog.component.html'
})
export class WebNotificationDeleteDialogComponent {
  webNotification?: IWebNotification;

  constructor(
    protected webNotificationService: WebNotificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.webNotificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('webNotificationListModification');
      this.activeModal.close();
    });
  }
}
