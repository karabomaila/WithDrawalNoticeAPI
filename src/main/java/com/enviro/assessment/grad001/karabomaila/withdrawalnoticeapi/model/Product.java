package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "current_balance", nullable = false)
    private double currentBalance;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    public void addToCurrentBalance(double amount){
        currentBalance += amount;
    }
}
