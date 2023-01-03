package com.pizzadeliverybackend.repositories;

import com.pizzadeliverybackend.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<OrderHistory, Long> {

}
