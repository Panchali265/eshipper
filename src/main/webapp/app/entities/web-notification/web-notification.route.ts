import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWebNotification, WebNotification } from 'app/shared/model/web-notification.model';
import { WebNotificationService } from './web-notification.service';
import { WebNotificationComponent } from './web-notification.component';
import { WebNotificationDetailComponent } from './web-notification-detail.component';
import { WebNotificationUpdateComponent } from './web-notification-update.component';

@Injectable({ providedIn: 'root' })
export class WebNotificationResolve implements Resolve<IWebNotification> {
  constructor(private service: WebNotificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWebNotification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((webNotification: HttpResponse<WebNotification>) => {
          if (webNotification.body) {
            return of(webNotification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WebNotification());
  }
}

export const webNotificationRoute: Routes = [
  {
    path: '',
    component: WebNotificationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.webNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WebNotificationDetailComponent,
    resolve: {
      webNotification: WebNotificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.webNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WebNotificationUpdateComponent,
    resolve: {
      webNotification: WebNotificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.webNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WebNotificationUpdateComponent,
    resolve: {
      webNotification: WebNotificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.webNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
