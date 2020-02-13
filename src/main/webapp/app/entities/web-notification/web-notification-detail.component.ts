import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWebNotification } from 'app/shared/model/web-notification.model';

@Component({
  selector: 'jhi-web-notification-detail',
  templateUrl: './web-notification-detail.component.html'
})
export class WebNotificationDetailComponent implements OnInit {
  webNotification: IWebNotification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ webNotification }) => (this.webNotification = webNotification));
  }

  previousState(): void {
    window.history.back();
  }
}
