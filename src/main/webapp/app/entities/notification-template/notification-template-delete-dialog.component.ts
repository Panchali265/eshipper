import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotificationTemplate } from 'app/shared/model/notification-template.model';
import { NotificationTemplateService } from './notification-template.service';

@Component({
  templateUrl: './notification-template-delete-dialog.component.html'
})
export class NotificationTemplateDeleteDialogComponent {
  notificationTemplate?: INotificationTemplate;

  constructor(
    protected notificationTemplateService: NotificationTemplateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notificationTemplateService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notificationTemplateListModification');
      this.activeModal.close();
    });
  }
}
