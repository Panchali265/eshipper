import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserNotificationSettings, UserNotificationSettings } from 'app/shared/model/user-notification-settings.model';
import { UserNotificationSettingsService } from './user-notification-settings.service';
import { IUser1 } from 'app/shared/model/user-1.model';
import { User1Service } from 'app/entities/user-1/user-1.service';

@Component({
  selector: 'jhi-user-notification-settings-update',
  templateUrl: './user-notification-settings-update.component.html'
})
export class UserNotificationSettingsUpdateComponent implements OnInit {
  isSaving = false;
  user1s: IUser1[] = [];

  editForm = this.fb.group({
    id: [],
    web: [],
    email: [],
    configurableMap: [],
    userId: []
  });

  constructor(
    protected userNotificationSettingsService: UserNotificationSettingsService,
    protected user1Service: User1Service,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userNotificationSettings }) => {
      this.updateForm(userNotificationSettings);

      this.user1Service.query().subscribe((res: HttpResponse<IUser1[]>) => (this.user1s = res.body || []));
    });
  }

  updateForm(userNotificationSettings: IUserNotificationSettings): void {
    this.editForm.patchValue({
      id: userNotificationSettings.id,
      web: userNotificationSettings.web,
      email: userNotificationSettings.email,
      configurableMap: userNotificationSettings.configurableMap,
      userId: userNotificationSettings.userId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userNotificationSettings = this.createFromForm();
    if (userNotificationSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.userNotificationSettingsService.update(userNotificationSettings));
    } else {
      this.subscribeToSaveResponse(this.userNotificationSettingsService.create(userNotificationSettings));
    }
  }

  private createFromForm(): IUserNotificationSettings {
    return {
      ...new UserNotificationSettings(),
      id: this.editForm.get(['id'])!.value,
      web: this.editForm.get(['web'])!.value,
      email: this.editForm.get(['email'])!.value,
      configurableMap: this.editForm.get(['configurableMap'])!.value,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserNotificationSettings>>): void {
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

  trackById(index: number, item: IUser1): any {
    return item.id;
  }
}
