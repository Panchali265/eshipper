import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotificationType, NotificationType } from 'app/shared/model/notification-type.model';
import { NotificationTypeService } from './notification-type.service';
import { INotificationCategory } from 'app/shared/model/notification-category.model';
import { NotificationCategoryService } from 'app/entities/notification-category/notification-category.service';

@Component({
  selector: 'jhi-notification-type-update',
  templateUrl: './notification-type-update.component.html'
})
export class NotificationTypeUpdateComponent implements OnInit {
  isSaving = false;
  notificationcategories: INotificationCategory[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    enumName: [],
    notificationCategoryId: []
  });

  constructor(
    protected notificationTypeService: NotificationTypeService,
    protected notificationCategoryService: NotificationCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationType }) => {
      this.updateForm(notificationType);

      this.notificationCategoryService
        .query()
        .subscribe((res: HttpResponse<INotificationCategory[]>) => (this.notificationcategories = res.body || []));
    });
  }

  updateForm(notificationType: INotificationType): void {
    this.editForm.patchValue({
      id: notificationType.id,
      name: notificationType.name,
      enumName: notificationType.enumName,
      notificationCategoryId: notificationType.notificationCategoryId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notificationType = this.createFromForm();
    if (notificationType.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationTypeService.update(notificationType));
    } else {
      this.subscribeToSaveResponse(this.notificationTypeService.create(notificationType));
    }
  }

  private createFromForm(): INotificationType {
    return {
      ...new NotificationType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      enumName: this.editForm.get(['enumName'])!.value,
      notificationCategoryId: this.editForm.get(['notificationCategoryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotificationType>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: INotificationCategory): any {
    return item.id;
  }
}
