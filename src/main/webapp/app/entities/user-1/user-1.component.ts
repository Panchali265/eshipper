import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUser1 } from 'app/shared/model/user-1.model';
import { User1Service } from './user-1.service';
import { User1DeleteDialogComponent } from './user-1-delete-dialog.component';

@Component({
  selector: 'jhi-user-1',
  templateUrl: './user-1.component.html'
})
export class User1Component implements OnInit, OnDestroy {
  user1S?: IUser1[];
  eventSubscriber?: Subscription;

  constructor(protected user1Service: User1Service, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.user1Service.query().subscribe((res: HttpResponse<IUser1[]>) => (this.user1S = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUser1S();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUser1): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUser1S(): void {
    this.eventSubscriber = this.eventManager.subscribe('user1ListModification', () => this.loadAll());
  }

  delete(user1: IUser1): void {
    const modalRef = this.modalService.open(User1DeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.user1 = user1;
  }
}
