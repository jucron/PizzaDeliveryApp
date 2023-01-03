package com.pizzadeliverybackend.repositories;

import com.pizzadeliverybackend.domain.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<ClientOrder, UUID> {
    List<ClientOrder> findByStatus(String confirmed);

//    @Query("SELECT o.id FROM ClientOrder o WHERE o.account.id = :accountId")
//    UUID getOrderIdByAccountId(long accountId);
}
