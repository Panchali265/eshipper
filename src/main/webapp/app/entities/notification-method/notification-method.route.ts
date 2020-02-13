import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotificationMethod, NotificationMethod } from 'app/shared/model/notification-method.model';
import { NotificationMethodService } from './notification-method.service';
import { NotificationMethodComponent } from './notification-method.component';
import { NotificationMethodDetailComponent } from './notification-method-detail.component';
import { NotificationMethodUpdateComponent } from './notification-method-update.component';

@Injectable({ providedIn: 'root' })
export class NotificationMethodResolve implements Resolve<INotificationMethod> {
  constructor(private service: NotificationMethodService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotificationMethod> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notificationMethod: HttpResponse<NotificationMethod>) => {
          if (notificationMethod.body) {
            return of(notificationMethod.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotificationMethod());
  }
}

export const notificationMethodRoute: Routes = [
  {
    path: '',
    component: NotificationMethodComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NotificationMethodDetailComponent,
    resolve: {
      notificationMethod: NotificationMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NotificationMethodUpdateComponent,
    resolve: {
      notificationMethod: NotificationMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NotificationMethodUpdateComponent,
    resolve: {
      notificationMethod: NotificationMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationMethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
