package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.repository;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Investor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends CrudRepository<Investor, Long> {
}
