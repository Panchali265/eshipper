import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotificationMethod } from 'app/shared/model/notification-method.model';

@Component({
  selector: 'jhi-notification-method-detail',
  templateUrl: './notification-method-detail.component.html'
})
export class NotificationMethodDetailComponent implements OnInit {
  notificationMethod: INotificationMethod | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationMethod }) => (this.notificationMethod = notificationMethod));
  }

  previousState(): void {
    window.history.back();
  }
}
