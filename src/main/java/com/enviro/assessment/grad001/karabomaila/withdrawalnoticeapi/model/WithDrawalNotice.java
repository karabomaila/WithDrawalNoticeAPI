package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class WithDrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "date")
    private LocalDate withDrawalDate;

    @Column(name = "product_type")
    private String productType;

    @OneToOne(mappedBy = "notice", cascade = CascadeType.ALL)
    private BankAccountInfo bankAccountInfo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
