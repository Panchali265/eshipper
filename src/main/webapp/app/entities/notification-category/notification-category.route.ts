import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotificationCategory, NotificationCategory } from 'app/shared/model/notification-category.model';
import { NotificationCategoryService } from './notification-category.service';
import { NotificationCategoryComponent } from './notification-category.component';
import { NotificationCategoryDetailComponent } from './notification-category-detail.component';
import { NotificationCategoryUpdateComponent } from './notification-category-update.component';

@Injectable({ providedIn: 'root' })
export class NotificationCategoryResolve implements Resolve<INotificationCategory> {
  constructor(private service: NotificationCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotificationCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notificationCategory: HttpResponse<NotificationCategory>) => {
          if (notificationCategory.body) {
            return of(notificationCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotificationCategory());
  }
}

export const notificationCategoryRoute: Routes = [
  {
    path: '',
    component: NotificationCategoryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NotificationCategoryDetailComponent,
    resolve: {
      notificationCategory: NotificationCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NotificationCategoryUpdateComponent,
    resolve: {
      notificationCategory: NotificationCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NotificationCategoryUpdateComponent,
    resolve: {
      notificationCategory: NotificationCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.notificationCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
