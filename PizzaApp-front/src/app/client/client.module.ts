import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ClientLoginComponent} from './client-login/client-login.component';
import {OrderComponent} from './order/order.component';
import {OrderStatusComponent} from './order-status/order-status.component';
import {FeedbackComponent} from './feedback/feedback.component';
import {ClientComponent} from './client.component';
import {RouterModule} from "@angular/router";
import {ClientRoutingModule} from "./client-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ClientActionComponent} from './client-action/client-action.component';
import {DialogStartProcessComponent} from './client-action/dialog-startprocess/dialog-start-process.component';
import {MatDialogModule} from "@angular/material/dialog";


@NgModule({
  declarations: [
    ClientLoginComponent,
    OrderComponent,
    OrderStatusComponent,
    FeedbackComponent,
    ClientComponent,
    ClientActionComponent,
    DialogStartProcessComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ClientRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule
  ],
  entryComponents: [DialogStartProcessComponent]
})
export class ClientModule {

}
