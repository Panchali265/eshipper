import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { NotificationTypeComponent } from './notification-type.component';
import { NotificationTypeDetailComponent } from './notification-type-detail.component';
import { NotificationTypeUpdateComponent } from './notification-type-update.component';
import { NotificationTypeDeleteDialogComponent } from './notification-type-delete-dialog.component';
import { notificationTypeRoute } from './notification-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(notificationTypeRoute)],
  declarations: [
    NotificationTypeComponent,
    NotificationTypeDetailComponent,
    NotificationTypeUpdateComponent,
    NotificationTypeDeleteDialogComponent
  ],
  entryComponents: [NotificationTypeDeleteDialogComponent]
})
export class EshipperNotificationTypeModule {}
