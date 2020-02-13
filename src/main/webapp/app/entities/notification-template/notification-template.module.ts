import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { NotificationTemplateComponent } from './notification-template.component';
import { NotificationTemplateDetailComponent } from './notification-template-detail.component';
import { NotificationTemplateUpdateComponent } from './notification-template-update.component';
import { NotificationTemplateDeleteDialogComponent } from './notification-template-delete-dialog.component';
import { notificationTemplateRoute } from './notification-template.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(notificationTemplateRoute)],
  declarations: [
    NotificationTemplateComponent,
    NotificationTemplateDetailComponent,
    NotificationTemplateUpdateComponent,
    NotificationTemplateDeleteDialogComponent
  ],
  entryComponents: [NotificationTemplateDeleteDialogComponent]
})
export class EshipperNotificationTemplateModule {}
