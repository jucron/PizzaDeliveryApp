package com.pizzadeliverybackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class ClientOrder {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String clientName;
    private String pizzaFlavor;
    private String address;
    private String status;
    private LocalTime orderTime;
    private boolean paid;

    @OneToOne(fetch = FetchType.EAGER)
    private OrderHistory orderHistory;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
}
