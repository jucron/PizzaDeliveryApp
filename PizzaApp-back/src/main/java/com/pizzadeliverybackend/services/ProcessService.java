package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.model.OrderMinimal;
import com.pizzadeliverybackend.model.Response;

public interface ProcessService {

    void startProcess(String key, String username);
    void completeTask(String username, Object object);
    Response getTaskDefKey(String username);
//    Response getOrderId(String username);
    Response getOrderStatus(String username);

    OrderMinimal getOrder(String username);
}
