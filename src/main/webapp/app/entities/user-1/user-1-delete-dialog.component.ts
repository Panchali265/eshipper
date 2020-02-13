import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUser1 } from 'app/shared/model/user-1.model';
import { User1Service } from './user-1.service';

@Component({
  templateUrl: './user-1-delete-dialog.component.html'
})
export class User1DeleteDialogComponent {
  user1?: IUser1;

  constructor(protected user1Service: User1Service, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.user1Service.delete(id).subscribe(() => {
      this.eventManager.broadcast('user1ListModification');
      this.activeModal.close();
    });
  }
}
