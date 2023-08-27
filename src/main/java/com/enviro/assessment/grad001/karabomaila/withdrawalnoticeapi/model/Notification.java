package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "amount_withdrawn")
    private double amountWithdrawn;

    @Column(name = "balance_before")
    private double balanceBefore;

    @Column(name = "closing_balance")
    private double closingBalance;
}
