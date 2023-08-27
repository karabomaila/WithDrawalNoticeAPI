package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.service;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Investor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@EnableScheduling
public class BatchService {
    private final InvestorService investorService;

    @Autowired
    public BatchService(InvestorService investorService) {
        this.investorService = investorService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateInvestorsAges(){
        List<Investor> investors = investorService.findAll();
        LocalDate localDate = LocalDate.now();
        for (Investor investor: investors){
            if (investor.getDateOfBirth().getMonthValue() == localDate.getMonth().getValue() && investor.getDateOfBirth().getDayOfMonth() == localDate.getDayOfMonth()){
                // updates the investor age and save the details
                investor.UpdateAge();
                investorService.addInvestor(investor);
            }
        }
    }
}
