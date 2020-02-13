import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserNotificationSettings } from 'app/shared/model/user-notification-settings.model';
import { UserNotificationSettingsService } from './user-notification-settings.service';
import { UserNotificationSettingsDeleteDialogComponent } from './user-notification-settings-delete-dialog.component';

@Component({
  selector: 'jhi-user-notification-settings',
  templateUrl: './user-notification-settings.component.html'
})
export class UserNotificationSettingsComponent implements OnInit, OnDestroy {
  userNotificationSettings?: IUserNotificationSettings[];
  eventSubscriber?: Subscription;

  constructor(
    protected userNotificationSettingsService: UserNotificationSettingsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userNotificationSettingsService
      .query()
      .subscribe((res: HttpResponse<IUserNotificationSettings[]>) => (this.userNotificationSettings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserNotificationSettings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserNotificationSettings): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserNotificationSettings(): void {
    this.eventSubscriber = this.eventManager.subscribe('userNotificationSettingsListModification', () => this.loadAll());
  }

  delete(userNotificationSettings: IUserNotificationSettings): void {
    const modalRef = this.modalService.open(UserNotificationSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userNotificationSettings = userNotificationSettings;
  }
}
