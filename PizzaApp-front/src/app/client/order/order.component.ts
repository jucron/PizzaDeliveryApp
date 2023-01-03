import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClientService, Order} from "../client.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit, AfterViewInit {
  orderForm: FormGroup;
  pizzaFlavors = [
    "Four cheese","Ham & Cheese","Tuna","Pepperoni","Veggie Pizza","Margherita","Hawaiian Pizza"];

  constructor(
    private formBuilder: FormBuilder,
    private clientService: ClientService
  ) { }

  ngOnInit(): void {
    this.orderForm = this.formBuilder.group({
      clientName: this.formBuilder.control('',Validators.required),
      pizzaFlavor: this.formBuilder.control('',Validators.required),
      address: this.formBuilder.control('',Validators.required),
      paid: this.formBuilder.control('')
    });
  }

  onSubmit(order: Order) {
    this.clientService.completeTask(order);
  }

  ngAfterViewInit(): void {
  }
}
