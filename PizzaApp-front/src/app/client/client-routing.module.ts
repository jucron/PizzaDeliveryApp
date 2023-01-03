import {RouterModule, Routes} from "@angular/router";
import {ClientLoginComponent} from "./client-login/client-login.component";
import {NgModule} from "@angular/core";
import {OrderComponent} from "./order/order.component";
import {OrderStatusComponent} from "./order-status/order-status.component";
import {FeedbackComponent} from "./feedback/feedback.component";
import {AlreadyAuthIn} from "./routerguards/already-auth.guard";
import {NoAuthGuard} from "./routerguards/no-auth.guard";
import {ClientActionComponent} from "./client-action/client-action.component";
import {ClientTaskGuard} from "./routerguards/client-task.guard";
import {ClientOrderGuard} from "./routerguards/client-order.guard";


const clientRoutes: Routes = [
  {path: '', component: ClientLoginComponent, canActivate: [AlreadyAuthIn]},
  {path: 'client-action', component: ClientActionComponent, canActivate: [NoAuthGuard, ClientTaskGuard]},
  {path: 'order', component: OrderComponent, canActivate: [NoAuthGuard]},
  {path: 'order-status', component: OrderStatusComponent, canActivate: [NoAuthGuard, ClientOrderGuard]},
  {path: 'feedback', component: FeedbackComponent, canActivate: [NoAuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(clientRoutes)],
  exports: [RouterModule]
})
export class ClientRoutingModule {}
