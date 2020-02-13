import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotificationTemplate } from 'app/shared/model/notification-template.model';

@Component({
  selector: 'jhi-notification-template-detail',
  templateUrl: './notification-template-detail.component.html'
})
export class NotificationTemplateDetailComponent implements OnInit {
  notificationTemplate: INotificationTemplate | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationTemplate }) => (this.notificationTemplate = notificationTemplate));
  }

  previousState(): void {
    window.history.back();
  }
}
