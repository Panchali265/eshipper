import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserNotificationSettings, UserNotificationSettings } from 'app/shared/model/user-notification-settings.model';
import { UserNotificationSettingsService } from './user-notification-settings.service';
import { UserNotificationSettingsComponent } from './user-notification-settings.component';
import { UserNotificationSettingsDetailComponent } from './user-notification-settings-detail.component';
import { UserNotificationSettingsUpdateComponent } from './user-notification-settings-update.component';

@Injectable({ providedIn: 'root' })
export class UserNotificationSettingsResolve implements Resolve<IUserNotificationSettings> {
  constructor(private service: UserNotificationSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserNotificationSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userNotificationSettings: HttpResponse<UserNotificationSettings>) => {
          if (userNotificationSettings.body) {
            return of(userNotificationSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserNotificationSettings());
  }
}

export const userNotificationSettingsRoute: Routes = [
  {
    path: '',
    component: UserNotificationSettingsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.userNotificationSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserNotificationSettingsDetailComponent,
    resolve: {
      userNotificationSettings: UserNotificationSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.userNotificationSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserNotificationSettingsUpdateComponent,
    resolve: {
      userNotificationSettings: UserNotificationSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.userNotificationSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserNotificationSettingsUpdateComponent,
    resolve: {
      userNotificationSettings: UserNotificationSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.userNotificationSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
