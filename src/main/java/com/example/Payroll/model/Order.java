package com.example.Payroll.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
    @Id
    @GeneratedValue
    private Long Id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
}
