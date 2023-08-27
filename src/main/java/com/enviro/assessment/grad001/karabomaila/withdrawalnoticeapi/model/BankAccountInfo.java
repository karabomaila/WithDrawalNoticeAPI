package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BankAccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String bankName;
    private String branchCode;
    private String accountHolderName;
    private String accountNumber;

    @OneToOne
    @JoinColumn(name = "notice_id", referencedColumnName = "id")
    private WithDrawalNotice notice;
}
