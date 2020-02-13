import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WebNotificationComponent } from './web-notification.component';
import { WebNotificationDetailComponent } from './web-notification-detail.component';
import { WebNotificationUpdateComponent } from './web-notification-update.component';
import { WebNotificationDeleteDialogComponent } from './web-notification-delete-dialog.component';
import { webNotificationRoute } from './web-notification.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(webNotificationRoute)],
  declarations: [
    WebNotificationComponent,
    WebNotificationDetailComponent,
    WebNotificationUpdateComponent,
    WebNotificationDeleteDialogComponent
  ],
  entryComponents: [WebNotificationDeleteDialogComponent]
})
export class EshipperWebNotificationModule {}
