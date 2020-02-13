import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotificationCategory } from 'app/shared/model/notification-category.model';

@Component({
  selector: 'jhi-notification-category-detail',
  templateUrl: './notification-category-detail.component.html'
})
export class NotificationCategoryDetailComponent implements OnInit {
  notificationCategory: INotificationCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationCategory }) => (this.notificationCategory = notificationCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
