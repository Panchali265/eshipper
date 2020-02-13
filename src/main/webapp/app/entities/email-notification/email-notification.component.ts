import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEmailNotification } from 'app/shared/model/email-notification.model';
import { EmailNotificationService } from './email-notification.service';
import { EmailNotificationDeleteDialogComponent } from './email-notification-delete-dialog.component';

@Component({
  selector: 'jhi-email-notification',
  templateUrl: './email-notification.component.html'
})
export class EmailNotificationComponent implements OnInit, OnDestroy {
  emailNotifications?: IEmailNotification[];
  eventSubscriber?: Subscription;

  constructor(
    protected emailNotificationService: EmailNotificationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.emailNotificationService
      .query()
      .subscribe((res: HttpResponse<IEmailNotification[]>) => (this.emailNotifications = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEmailNotifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEmailNotification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEmailNotifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('emailNotificationListModification', () => this.loadAll());
  }

  delete(emailNotification: IEmailNotification): void {
    const modalRef = this.modalService.open(EmailNotificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.emailNotification = emailNotification;
  }
}
