import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEmailNotification, EmailNotification } from 'app/shared/model/email-notification.model';
import { EmailNotificationService } from './email-notification.service';
import { EmailNotificationComponent } from './email-notification.component';
import { EmailNotificationDetailComponent } from './email-notification-detail.component';
import { EmailNotificationUpdateComponent } from './email-notification-update.component';

@Injectable({ providedIn: 'root' })
export class EmailNotificationResolve implements Resolve<IEmailNotification> {
  constructor(private service: EmailNotificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmailNotification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((emailNotification: HttpResponse<EmailNotification>) => {
          if (emailNotification.body) {
            return of(emailNotification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EmailNotification());
  }
}

export const emailNotificationRoute: Routes = [
  {
    path: '',
    component: EmailNotificationComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.emailNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EmailNotificationDetailComponent,
    resolve: {
      emailNotification: EmailNotificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.emailNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmailNotificationUpdateComponent,
    resolve: {
      emailNotification: EmailNotificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.emailNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EmailNotificationUpdateComponent,
    resolve: {
      emailNotification: EmailNotificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.emailNotification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
