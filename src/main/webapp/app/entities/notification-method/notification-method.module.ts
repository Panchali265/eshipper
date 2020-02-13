import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { NotificationMethodComponent } from './notification-method.component';
import { NotificationMethodDetailComponent } from './notification-method-detail.component';
import { NotificationMethodUpdateComponent } from './notification-method-update.component';
import { NotificationMethodDeleteDialogComponent } from './notification-method-delete-dialog.component';
import { notificationMethodRoute } from './notification-method.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(notificationMethodRoute)],
  declarations: [
    NotificationMethodComponent,
    NotificationMethodDetailComponent,
    NotificationMethodUpdateComponent,
    NotificationMethodDeleteDialogComponent
  ],
  entryComponents: [NotificationMethodDeleteDialogComponent]
})
export class EshipperNotificationMethodModule {}
