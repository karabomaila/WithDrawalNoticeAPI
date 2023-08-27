package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.repository;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
