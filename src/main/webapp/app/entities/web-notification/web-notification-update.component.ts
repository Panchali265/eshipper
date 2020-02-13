import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IWebNotification, WebNotification } from 'app/shared/model/web-notification.model';
import { WebNotificationService } from './web-notification.service';
import { INotificationTemplate } from 'app/shared/model/notification-template.model';
import { NotificationTemplateService } from 'app/entities/notification-template/notification-template.service';
import { IUser1 } from 'app/shared/model/user-1.model';
import { User1Service } from 'app/entities/user-1/user-1.service';

type SelectableEntity = INotificationTemplate | IUser1;

@Component({
  selector: 'jhi-web-notification-update',
  templateUrl: './web-notification-update.component.html'
})
export class WebNotificationUpdateComponent implements OnInit {
  isSaving = false;
  notificationtemplates: INotificationTemplate[] = [];
  user1s: IUser1[] = [];

  editForm = this.fb.group({
    id: [],
    url: [],
    isRead: [],
    webMap: [],
    notificationTemplateId: [],
    createdById: [],
    toUserId: []
  });

  constructor(
    protected webNotificationService: WebNotificationService,
    protected notificationTemplateService: NotificationTemplateService,
    protected user1Service: User1Service,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ webNotification }) => {
      this.updateForm(webNotification);

      this.notificationTemplateService
        .query({ filter: 'webnotification-is-null' })
        .pipe(
          map((res: HttpResponse<INotificationTemplate[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: INotificationTemplate[]) => {
          if (!webNotification.notificationTemplateId) {
            this.notificationtemplates = resBody;
          } else {
            this.notificationTemplateService
              .find(webNotification.notificationTemplateId)
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

  updateForm(webNotification: IWebNotification): void {
    this.editForm.patchValue({
      id: webNotification.id,
      url: webNotification.url,
      isRead: webNotification.isRead,
      webMap: webNotification.webMap,
      notificationTemplateId: webNotification.notificationTemplateId,
      createdById: webNotification.createdById,
      toUserId: webNotification.toUserId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const webNotification = this.createFromForm();
    if (webNotification.id !== undefined) {
      this.subscribeToSaveResponse(this.webNotificationService.update(webNotification));
    } else {
      this.subscribeToSaveResponse(this.webNotificationService.create(webNotification));
    }
  }

  private createFromForm(): IWebNotification {
    return {
      ...new WebNotification(),
      id: this.editForm.get(['id'])!.value,
      url: this.editForm.get(['url'])!.value,
      isRead: this.editForm.get(['isRead'])!.value,
      webMap: this.editForm.get(['webMap'])!.value,
      notificationTemplateId: this.editForm.get(['notificationTemplateId'])!.value,
      createdById: this.editForm.get(['createdById'])!.value,
      toUserId: this.editForm.get(['toUserId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWebNotification>>): void {
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
