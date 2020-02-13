import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-1',
        loadChildren: () => import('./user-1/user-1.module').then(m => m.EshipperUser1Module)
      },
      {
        path: 'notification-category',
        loadChildren: () => import('./notification-category/notification-category.module').then(m => m.EshipperNotificationCategoryModule)
      },
      {
        path: 'notification-type',
        loadChildren: () => import('./notification-type/notification-type.module').then(m => m.EshipperNotificationTypeModule)
      },
      {
        path: 'notification-method',
        loadChildren: () => import('./notification-method/notification-method.module').then(m => m.EshipperNotificationMethodModule)
      },
      {
        path: 'notification-template',
        loadChildren: () => import('./notification-template/notification-template.module').then(m => m.EshipperNotificationTemplateModule)
      },
      {
        path: 'email-notification',
        loadChildren: () => import('./email-notification/email-notification.module').then(m => m.EshipperEmailNotificationModule)
      },
      {
        path: 'web-notification',
        loadChildren: () => import('./web-notification/web-notification.module').then(m => m.EshipperWebNotificationModule)
      },
      {
        path: 'user-notification-settings',
        loadChildren: () =>
          import('./user-notification-settings/user-notification-settings.module').then(m => m.EshipperUserNotificationSettingsModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EshipperEntityModule {}
