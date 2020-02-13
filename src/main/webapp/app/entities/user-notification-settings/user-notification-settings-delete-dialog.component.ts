import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserNotificationSettings } from 'app/shared/model/user-notification-settings.model';
import { UserNotificationSettingsService } from './user-notification-settings.service';

@Component({
  templateUrl: './user-notification-settings-delete-dialog.component.html'
})
export class UserNotificationSettingsDeleteDialogComponent {
  userNotificationSettings?: IUserNotificationSettings;

  constructor(
    protected userNotificationSettingsService: UserNotificationSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userNotificationSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userNotificationSettingsListModification');
      this.activeModal.close();
    });
  }
}
