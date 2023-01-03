import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ClientService, Order} from "../client.service";
import {interval} from "rxjs";
import {ClientOrderGuard} from "../routerguards/client-order.guard";

@Component({
  selector: 'app-order-status',
  templateUrl: './order-status.component.html',
  styleUrls: ['./order-status.component.scss']
})
export class OrderStatusComponent implements OnInit, AfterViewInit {
  order: Order;
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
    let sub = interval(milliseconds).subscribe(() => {
      let currentUrl = this.clientService.getCurrentUrl();
      if (this.order.status=='finished' || currentUrl != '/client/order-status') {
        // console.log(currentUrl)
        console.log("updateOrder: function is no longer necessary, shutting it down.")
        sub.unsubscribe();
      } else {
        console.log(milliseconds/1000+' seconds passed: refreshing page again with new Order')
        this.clientService.redirectTo('client/order-status',true);
      }
    })
  }

  assignOrder () {
    this.order = ClientOrderGuard.order;
}

  closeTask() {
  }

  forwardStatus() {
    this.order.status=this.statusCode[this.statusCode.indexOf(this.order.status)+1]
  }
}
