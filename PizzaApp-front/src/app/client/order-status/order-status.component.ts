import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ClientService, Order} from "../client.service";
import {interval, Subscription} from "rxjs";
import {ClientOrderGuard} from "../routerguards/client-order.guard";

@Component({
  selector: 'app-order-status',
  templateUrl: './order-status.component.html',
  styleUrls: ['./order-status.component.scss']
})
export class OrderStatusComponent implements OnInit, AfterViewInit {
  order: Order;
  intervalSubscription: Subscription;
  statusCode = ['confirmed','accepted','baking','pizzaReady','delivering','pizzaDelivered','finished'];
  statusView = ['Confirmed','Accepted','Baking','Pizza Ready','Delivering','Delivered','Finished'];

  constructor(
    private clientService: ClientService) { }

  ngOnInit(): void {
    this.assignOrder();
  }

  ngAfterViewInit(): void {
    this.assignOrder();
    this.updateOrder().then();
  }

  async updateOrder() {
    let milliseconds = 20000;
    console.log('updateOrder: function started, will refresh page after '+milliseconds/1000+' seconds.')
    this.intervalSubscription = interval(milliseconds).subscribe(() => {
      let currentUrl = this.clientService.getCurrentUrl();
      if (this.order.status=='finished' || currentUrl != '/client/order-status') {
        this.closeIntervalSubscription();
      } else {
        console.log(milliseconds/1000+' seconds passed: refreshing page again with new Order')
        this.clientService.redirectTo('client/order-status',true);
      }
    })
  }

  assignOrder () {
    this.order = ClientOrderGuard.order;
}

closeIntervalSubscription () {
    console.log("updateOrder: function is no longer necessary, shutting it down.")
    this.intervalSubscription.unsubscribe();
}

  closeTask() {
  }

  forwardStatus() {
    this.order.status=this.statusCode[this.statusCode.indexOf(this.order.status)+1]
  }
}
