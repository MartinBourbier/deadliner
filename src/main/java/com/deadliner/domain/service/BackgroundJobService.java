package com.deadliner.domain.service;

import com.deadliner.data.repository.DeadlineRepository;
import com.deadliner.utils.DeadlineStatus;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class BackgroundJobService {
    @Inject DeadlineRepository deadlineRepository;

    @Scheduled(every = "1m")
    @Transactional
    public void notifyDeadline() {
        deadlineRepository.findAll().stream().forEach(deadlineModel -> {
            if (deadlineModel.status == DeadlineStatus.PUBLIC
                && ChronoUnit.HOURS.between(deadlineModel.deadlineDateTime, LocalDateTime.now()) < 24) {
                System.out.println("Deadline " + deadlineModel.label + " is coming!!");
            }
        });
    }
}
