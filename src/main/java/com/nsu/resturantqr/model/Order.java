package com.nsu.resturantqr.model;

import com.nsu.resturantqr.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tableId;
    private String items;
    private double totalAmount;

    @Enumerated(value = EnumType.STRING)
    private Status status;
}
