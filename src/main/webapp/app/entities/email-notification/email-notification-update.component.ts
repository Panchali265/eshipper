import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEmailNotification, EmailNotification } from 'app/shared/model/email-notification.model';
import { EmailNotificationService } from './email-notification.service';
import { INotificationTemplate } from 'app/shared/model/notification-template.model';
import { NotificationTemplateService } from 'app/entities/notification-template/notification-template.service';
import { IUser1 } from 'app/shared/model/user-1.model';
import { User1Service } from 'app/entities/user-1/user-1.service';

type SelectableEntity = INotificationTemplate | IUser1;

@Component({
  selector: 'jhi-email-notification-update',
  templateUrl: './email-notification-update.component.html'
})
export class EmailNotificationUpdateComponent implements OnInit {
  isSaving = false;
  notificationtemplates: INotificationTemplate[] = [];
  user1s: IUser1[] = [];

  editForm = this.fb.group({
    id: [],
    toEmail: [],
    emailMap: [],
    notificationTemplateId: [],
    createdById: [],
    toUserId: []
  });

  constructor(
    protected emailNotificationService: EmailNotificationService,
    protected notificationTemplateService: NotificationTemplateService,
    protected user1Service: User1Service,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emailNotification }) => {
      this.updateForm(emailNotification);

      this.notificationTemplateService
        .query({ filter: 'emailnotification-is-null' })
        .pipe(
          map((res: HttpResponse<INotificationTemplate[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: INotificationTemplate[]) => {
          if (!emailNotification.notificationTemplateId) {
            this.notificationtemplates = resBody;
          } else {
            this.notificationTemplateService
              .find(emailNotification.notificationTemplateId)
              .pipe(
                map((subRes: HttpResponse<INotificationTemplate>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: INotificationTemplate[]) => (this.notificationtemplates = concatRes));
          }
        });

      this.user1Service.query().subscribe((res: HttpResponse<IUser1[]>) => (this.user1s = res.body || []));
    });
  }

  updateForm(emailNotification: IEmailNotification): void {
    this.editForm.patchValue({
      id: emailNotification.id,
      toEmail: emailNotification.toEmail,
      emailMap: emailNotification.emailMap,
      notificationTemplateId: emailNotification.notificationTemplateId,
      createdById: emailNotification.createdById,
      toUserId: emailNotification.toUserId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const emailNotification = this.createFromForm();
    if (emailNotification.id !== undefined) {
      this.subscribeToSaveResponse(this.emailNotificationService.update(emailNotification));
    } else {
      this.subscribeToSaveResponse(this.emailNotificationService.create(emailNotification));
    }
  }

  private createFromForm(): IEmailNotification {
    return {
      ...new EmailNotification(),
      id: this.editForm.get(['id'])!.value,
      toEmail: this.editForm.get(['toEmail'])!.value,
      emailMap: this.editForm.get(['emailMap'])!.value,
      notificationTemplateId: this.editForm.get(['notificationTemplateId'])!.value,
      createdById: this.editForm.get(['createdById'])!.value,
      toUserId: this.editForm.get(['toUserId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmailNotification>>): void {
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
