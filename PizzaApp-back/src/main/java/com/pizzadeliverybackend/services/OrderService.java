package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.OrderHistory;
import com.pizzadeliverybackend.model.EntityList;
import com.pizzadeliverybackend.model.OrderMinimal;


public interface OrderService {
    EntityList<ClientOrder> getOrders();

    EntityList<ClientOrder> getConfirmedOrders();

    void changeOrderStatus(String orderId, String orderStatus);

    ClientOrder createOrder(ClientOrder order);

    void deleteOrder(String orderId);

    EntityList<ClientOrder> getAcceptedOrders();

    EntityList<ClientOrder> getFinishedOrders();

    OrderMinimal getOrder(String orderId);

    void updateHistoryOrder(String orderId, OrderHistory orderHistory);

    ClientOrder getOrderByUsername(String username);
}
