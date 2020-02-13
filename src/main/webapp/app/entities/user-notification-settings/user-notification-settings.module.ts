import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { UserNotificationSettingsComponent } from './user-notification-settings.component';
import { UserNotificationSettingsDetailComponent } from './user-notification-settings-detail.component';
import { UserNotificationSettingsUpdateComponent } from './user-notification-settings-update.component';
import { UserNotificationSettingsDeleteDialogComponent } from './user-notification-settings-delete-dialog.component';
import { userNotificationSettingsRoute } from './user-notification-settings.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(userNotificationSettingsRoute)],
  declarations: [
    UserNotificationSettingsComponent,
    UserNotificationSettingsDetailComponent,
    UserNotificationSettingsUpdateComponent,
    UserNotificationSettingsDeleteDialogComponent
  ],
  entryComponents: [UserNotificationSettingsDeleteDialogComponent]
})
export class EshipperUserNotificationSettingsModule {}
