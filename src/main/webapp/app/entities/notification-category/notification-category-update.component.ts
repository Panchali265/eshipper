import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotificationCategory, NotificationCategory } from 'app/shared/model/notification-category.model';
import { NotificationCategoryService } from './notification-category.service';

@Component({
  selector: 'jhi-notification-category-update',
  templateUrl: './notification-category-update.component.html'
})
export class NotificationCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    role: []
  });

  constructor(
    protected notificationCategoryService: NotificationCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationCategory }) => {
      this.updateForm(notificationCategory);
    });
  }

  updateForm(notificationCategory: INotificationCategory): void {
    this.editForm.patchValue({
      id: notificationCategory.id,
      name: notificationCategory.name,
      role: notificationCategory.role
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notificationCategory = this.createFromForm();
    if (notificationCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationCategoryService.update(notificationCategory));
    } else {
      this.subscribeToSaveResponse(this.notificationCategoryService.create(notificationCategory));
    }
  }

  private createFromForm(): INotificationCategory {
    return {
      ...new NotificationCategory(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      role: this.editForm.get(['role'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotificationCategory>>): void {
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
}
