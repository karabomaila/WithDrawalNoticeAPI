package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.repository;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.WithDrawalNotice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WithDrawalNoticeRepository extends CrudRepository<WithDrawalNotice, Long> {
    public List<WithDrawalNotice> findByProductTypeAndWithDrawalDateBetween(String productType, LocalDate startDate, LocalDate endDate);
}
