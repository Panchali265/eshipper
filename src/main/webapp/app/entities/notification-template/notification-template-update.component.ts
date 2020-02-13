import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotificationTemplate, NotificationTemplate } from 'app/shared/model/notification-template.model';
import { NotificationTemplateService } from './notification-template.service';
import { INotificationMethod } from 'app/shared/model/notification-method.model';
import { NotificationMethodService } from 'app/entities/notification-method/notification-method.service';
import { INotificationType } from 'app/shared/model/notification-type.model';
import { NotificationTypeService } from 'app/entities/notification-type/notification-type.service';

type SelectableEntity = INotificationMethod | INotificationType;

@Component({
  selector: 'jhi-notification-template-update',
  templateUrl: './notification-template-update.component.html'
})
export class NotificationTemplateUpdateComponent implements OnInit {
  isSaving = false;
  notificationmethods: INotificationMethod[] = [];
  notificationtypes: INotificationType[] = [];

  editForm = this.fb.group({
    id: [],
    subject: [],
    description: [],
    notificationMethodId: [],
    notificationTypeId: []
  });

  constructor(
    protected notificationTemplateService: NotificationTemplateService,
    protected notificationMethodService: NotificationMethodService,
    protected notificationTypeService: NotificationTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationTemplate }) => {
      this.updateForm(notificationTemplate);

      this.notificationMethodService
        .query()
        .subscribe((res: HttpResponse<INotificationMethod[]>) => (this.notificationmethods = res.body || []));

      this.notificationTypeService.query().subscribe((res: HttpResponse<INotificationType[]>) => (this.notificationtypes = res.body || []));
    });
  }

  updateForm(notificationTemplate: INotificationTemplate): void {
    this.editForm.patchValue({
      id: notificationTemplate.id,
      subject: notificationTemplate.subject,
      description: notificationTemplate.description,
      notificationMethodId: notificationTemplate.notificationMethodId,
      notificationTypeId: notificationTemplate.notificationTypeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notificationTemplate = this.createFromForm();
    if (notificationTemplate.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationTemplateService.update(notificationTemplate));
    } else {
      this.subscribeToSaveResponse(this.notificationTemplateService.create(notificationTemplate));
    }
  }

  private createFromForm(): INotificationTemplate {
    return {
      ...new NotificationTemplate(),
      id: this.editForm.get(['id'])!.value,
      subject: this.editForm.get(['subject'])!.value,
      description: this.editForm.get(['description'])!.value,
      notificationMethodId: this.editForm.get(['notificationMethodId'])!.value,
      notificationTypeId: this.editForm.get(['notificationTypeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotificationTemplate>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
