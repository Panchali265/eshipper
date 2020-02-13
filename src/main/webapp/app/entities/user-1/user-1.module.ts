import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { User1Component } from './user-1.component';
import { User1DetailComponent } from './user-1-detail.component';
import { User1UpdateComponent } from './user-1-update.component';
import { User1DeleteDialogComponent } from './user-1-delete-dialog.component';
import { user1Route } from './user-1.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(user1Route)],
  declarations: [User1Component, User1DetailComponent, User1UpdateComponent, User1DeleteDialogComponent],
  entryComponents: [User1DeleteDialogComponent]
})
export class EshipperUser1Module {}
