import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EmailNotificationComponent } from './email-notification.component';
import { EmailNotificationDetailComponent } from './email-notification-detail.component';
import { EmailNotificationUpdateComponent } from './email-notification-update.component';
import { EmailNotificationDeleteDialogComponent } from './email-notification-delete-dialog.component';
import { emailNotificationRoute } from './email-notification.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(emailNotificationRoute)],
  declarations: [
    EmailNotificationComponent,
    EmailNotificationDetailComponent,
    EmailNotificationUpdateComponent,
    EmailNotificationDeleteDialogComponent
  ],
  entryComponents: [EmailNotificationDeleteDialogComponent]
})
export class EshipperEmailNotificationModule {}
