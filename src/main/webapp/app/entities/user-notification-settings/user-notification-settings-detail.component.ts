import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserNotificationSettings } from 'app/shared/model/user-notification-settings.model';

@Component({
  selector: 'jhi-user-notification-settings-detail',
  templateUrl: './user-notification-settings-detail.component.html'
})
export class UserNotificationSettingsDetailComponent implements OnInit {
  userNotificationSettings: IUserNotificationSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userNotificationSettings }) => (this.userNotificationSettings = userNotificationSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
