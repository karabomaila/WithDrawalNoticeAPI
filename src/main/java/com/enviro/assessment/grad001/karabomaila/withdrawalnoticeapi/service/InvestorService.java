package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.service;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Investor;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.WithDrawalNotice;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.repository.InvestorRepository;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.repository.WithDrawalNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;
    private final WithDrawalNoticeRepository withDrawalNoticeRepository;
    @Autowired
    public InvestorService(InvestorRepository investorRepository, WithDrawalNoticeRepository withDrawalNoticeRepository) {
        this.investorRepository = investorRepository;
        this.withDrawalNoticeRepository = withDrawalNoticeRepository;
    }

    public Optional<Investor> findInvestorById(long id){
        return investorRepository.findById(id);
    }

    public void addInvestor(Investor investor) {
        investorRepository.save(investor);
    }

    public void addNotice(WithDrawalNotice withDrawalNotice){
        withDrawalNoticeRepository.save(withDrawalNotice);
    }

    public Optional<WithDrawalNotice> findById(long id){
        return withDrawalNoticeRepository.findById(id);
    }

    public List<Investor> findAll() {
        return (List<Investor>) investorRepository.findAll();
    }

    public List<WithDrawalNotice> findWithDrawalNoticeByDateBetween(String productType, LocalDate startDate, LocalDate endDate){
        return withDrawalNoticeRepository.findByProductTypeAndWithDrawalDateBetween(productType, startDate, endDate);
    }
}
