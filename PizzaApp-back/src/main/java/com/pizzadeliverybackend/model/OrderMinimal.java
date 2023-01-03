package com.pizzadeliverybackend.model;

import com.pizzadeliverybackend.domain.ClientOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class OrderMinimal {
    private String id;
    private String clientName;
    private String pizzaFlavor;
    private String address;
    private String status;
    private String orderTime;
    private boolean paid;

    public OrderMinimal(ClientOrder order) {
        this.id = order.getId().toString();
        this.clientName = order.getClientName();
        this.pizzaFlavor = order.getPizzaFlavor();
        this.address = order.getAddress();
        this.status = order.getStatus();
        this.orderTime = order.getOrderTime().toString();
        this.paid = order.isPaid();
    }
}
