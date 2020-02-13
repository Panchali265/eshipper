import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotificationTemplate, NotificationTemplate } from 'app/shared/model/notification-template.model';
import { NotificationTemplateService } from './notification-template.service';
import { NotificationTemplateComponent } from './notification-template.component';
import { NotificationTemplateDetailComponent } from './notification-template-detail.component';
import { NotificationTemplateUpdateComponent } from './notification-template-update.component';

@Injectable({ providedIn: 'root' })
export class NotificationTemplateResolve implements Resolve<INotificationTemplate> {
  constructor(private service: NotificationTemplateService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotificationTemplate> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notificationTemplate: HttpResponse<NotificationTemplate>) => {
          if (notificationTemplate.body) {
            return of(notificationTemplate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotificationTemplate());
  }
}

export const notificationTemplateRoute: Routes = [
  {
    path: '',
    component: NotificationTemplateComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NotificationTemplateDetailComponent,
    resolve: {
      notificationTemplate: NotificationTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NotificationTemplateUpdateComponent,
    resolve: {
      notificationTemplate: NotificationTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NotificationTemplateUpdateComponent,
    resolve: {
      notificationTemplate: NotificationTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
