package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.OrderHistory;
import com.pizzadeliverybackend.model.EntityList;
import com.pizzadeliverybackend.model.OrderMinimal;
import com.pizzadeliverybackend.repositories.AccountRepository;
import com.pizzadeliverybackend.repositories.HistoryRepository;
import com.pizzadeliverybackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final HistoryRepository historyRepository;

    private final AccountRepository accountRepository;
    @Override
    public EntityList<ClientOrder> getOrders() {
        List<ClientOrder> repositoryAll = orderRepository.findAll();
        return new EntityList<ClientOrder>()
                .withCount(repositoryAll.size())
                .withEntityList(repositoryAll);
    }

    @Override
    public EntityList<ClientOrder> getConfirmedOrders() {
        List<ClientOrder> repositoryByStatus = orderRepository.findByStatus("confirmed");
        return new EntityList<ClientOrder>()
                .withCount(repositoryByStatus.size())
                .withEntityList(repositoryByStatus);
    }

    @Override
    public EntityList<ClientOrder> getAcceptedOrders() {
        List<ClientOrder> clientOrderList = Stream.of(
                        orderRepository.findByStatus("accepted"),
                        orderRepository.findByStatus("baking"),
                        orderRepository.findByStatus("pizzaReady"),
                        orderRepository.findByStatus("delivering"),
                        orderRepository.findByStatus("pizzaDelivered")
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new EntityList<ClientOrder>()
                .withCount(clientOrderList.size())
                .withEntityList(clientOrderList);
    }

    @Override
    public EntityList<ClientOrder> getFinishedOrders() {
        List<ClientOrder> repositoryByStatus = orderRepository.findByStatus("finished");
        return new EntityList<ClientOrder>()
                .withCount(repositoryByStatus.size())
                .withEntityList(repositoryByStatus);
    }

    @Override
    public OrderMinimal getOrder(String orderId) {
        ClientOrder order = orderRepository.findById(UUID.fromString(orderId)).orElse(null);
        log.info("Order found: "+order);
        return order==null ? null : new OrderMinimal(order);
    }

    @Override
    public void updateHistoryOrder(String orderId, OrderHistory orderHistory) {
        ClientOrder orderInRepo = orderRepository.findById(UUID.fromString(orderId)).get();
        OrderHistory orderHistoryInRepo = orderInRepo.getOrderHistory();

        if (orderHistory.getClientFeedbackNotes()!=null) { //history from client
            orderHistoryInRepo.setClientFeedbackScore(orderHistory.getClientFeedbackScore());
            orderHistoryInRepo.setClientFeedbackNotes(orderHistory.getClientFeedbackNotes());
        } else { //history from delivery
            orderHistoryInRepo.setDeliveryFeedback(orderHistory.getDeliveryFeedback());
            orderHistoryInRepo.setDeliveryTime(LocalTime.now());
        }
        historyRepository.save(orderHistoryInRepo);
    }

    @Override
    public ClientOrder getOrderByUsername(String username) {
        log.info("getOrderByUsername accessed");
        return orderRepository.findAll().stream()
                .filter(clientOrder ->
                Objects.equals(clientOrder.getAccount().getUsername(),
                        username))
                .collect(Collectors.toList()).get(0);
    }


    @Override
    public void changeOrderStatus(String orderId, String orderStatus) {
        ClientOrder orderToBeUpdated = orderRepository.findById(UUID.fromString(orderId)).get();
        orderToBeUpdated.setStatus(orderStatus);
        orderRepository.save(orderToBeUpdated);
    }

    @Override
    public ClientOrder createOrder(ClientOrder order) {
        log.info("CreateOrder executed");
        order.setOrderTime(LocalTime.now());
        order.setStatus("confirmed");
        order.setOrderHistory(historyRepository.save(new OrderHistory()));

        Account account = accountRepository.findByUsername(order.getAccount().getUsername()).get();
        order.setAccount(account);

        ClientOrder orderSaved = orderRepository.save(order);
        log.info("Order created: " + orderSaved);
        return orderSaved;
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(UUID.fromString(orderId));
    }
}
