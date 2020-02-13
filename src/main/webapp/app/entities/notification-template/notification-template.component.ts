import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotificationTemplate } from 'app/shared/model/notification-template.model';
import { NotificationTemplateService } from './notification-template.service';
import { NotificationTemplateDeleteDialogComponent } from './notification-template-delete-dialog.component';

@Component({
  selector: 'jhi-notification-template',
  templateUrl: './notification-template.component.html'
})
export class NotificationTemplateComponent implements OnInit, OnDestroy {
  notificationTemplates?: INotificationTemplate[];
  eventSubscriber?: Subscription;

  constructor(
    protected notificationTemplateService: NotificationTemplateService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.notificationTemplateService
      .query()
      .subscribe((res: HttpResponse<INotificationTemplate[]>) => (this.notificationTemplates = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotificationTemplates();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotificationTemplate): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotificationTemplates(): void {
    this.eventSubscriber = this.eventManager.subscribe('notificationTemplateListModification', () => this.loadAll());
  }

  delete(notificationTemplate: INotificationTemplate): void {
    const modalRef = this.modalService.open(NotificationTemplateDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notificationTemplate = notificationTemplate;
  }
}
