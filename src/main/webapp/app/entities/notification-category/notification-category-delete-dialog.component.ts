import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotificationCategory } from 'app/shared/model/notification-category.model';
import { NotificationCategoryService } from './notification-category.service';

@Component({
  templateUrl: './notification-category-delete-dialog.component.html'
})
export class NotificationCategoryDeleteDialogComponent {
  notificationCategory?: INotificationCategory;

  constructor(
    protected notificationCategoryService: NotificationCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notificationCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notificationCategoryListModification');
      this.activeModal.close();
    });
  }
}
