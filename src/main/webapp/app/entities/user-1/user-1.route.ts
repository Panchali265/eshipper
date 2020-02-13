import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUser1, User1 } from 'app/shared/model/user-1.model';
import { User1Service } from './user-1.service';
import { User1Component } from './user-1.component';
import { User1DetailComponent } from './user-1-detail.component';
import { User1UpdateComponent } from './user-1-update.component';

@Injectable({ providedIn: 'root' })
export class User1Resolve implements Resolve<IUser1> {
  constructor(private service: User1Service, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUser1> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((user1: HttpResponse<User1>) => {
          if (user1.body) {
            return of(user1.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new User1());
  }
}

export const user1Route: Routes = [
  {
    path: '',
    component: User1Component,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user1.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: User1DetailComponent,
    resolve: {
      user1: User1Resolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user1.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: User1UpdateComponent,
    resolve: {
      user1: User1Resolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user1.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: User1UpdateComponent,
    resolve: {
      user1: User1Resolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.user1.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
