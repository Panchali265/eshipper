import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { NotificationCategoryComponent } from './notification-category.component';
import { NotificationCategoryDetailComponent } from './notification-category-detail.component';
import { NotificationCategoryUpdateComponent } from './notification-category-update.component';
import { NotificationCategoryDeleteDialogComponent } from './notification-category-delete-dialog.component';
import { notificationCategoryRoute } from './notification-category.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(notificationCategoryRoute)],
  declarations: [
    NotificationCategoryComponent,
    NotificationCategoryDetailComponent,
    NotificationCategoryUpdateComponent,
    NotificationCategoryDeleteDialogComponent
  ],
  entryComponents: [NotificationCategoryDeleteDialogComponent]
})
export class EshipperNotificationCategoryModule {}
