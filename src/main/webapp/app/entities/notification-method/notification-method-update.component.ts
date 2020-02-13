import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotificationMethod, NotificationMethod } from 'app/shared/model/notification-method.model';
import { NotificationMethodService } from './notification-method.service';

@Component({
  selector: 'jhi-notification-method-update',
  templateUrl: './notification-method-update.component.html'
})
export class NotificationMethodUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    methodName: []
  });

  constructor(
    protected notificationMethodService: NotificationMethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationMethod }) => {
      this.updateForm(notificationMethod);
    });
  }

  updateForm(notificationMethod: INotificationMethod): void {
    this.editForm.patchValue({
      id: notificationMethod.id,
      methodName: notificationMethod.methodName
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notificationMethod = this.createFromForm();
    if (notificationMethod.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationMethodService.update(notificationMethod));
    } else {
      this.subscribeToSaveResponse(this.notificationMethodService.create(notificationMethod));
    }
  }

  private createFromForm(): INotificationMethod {
    return {
      ...new NotificationMethod(),
      id: this.editForm.get(['id'])!.value,
      methodName: this.editForm.get(['methodName'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotificationMethod>>): void {
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
