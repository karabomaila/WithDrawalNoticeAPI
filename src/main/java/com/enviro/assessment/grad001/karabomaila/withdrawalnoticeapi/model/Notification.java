package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Notification {
    private double amountWithdrawn;

    private double balanceBefore;

    private double closingBalance;
}
