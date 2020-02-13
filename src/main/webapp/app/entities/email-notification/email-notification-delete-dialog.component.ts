import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmailNotification } from 'app/shared/model/email-notification.model';
import { EmailNotificationService } from './email-notification.service';

@Component({
  templateUrl: './email-notification-delete-dialog.component.html'
})
export class EmailNotificationDeleteDialogComponent {
  emailNotification?: IEmailNotification;

  constructor(
    protected emailNotificationService: EmailNotificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.emailNotificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('emailNotificationListModification');
      this.activeModal.close();
    });
  }
}
