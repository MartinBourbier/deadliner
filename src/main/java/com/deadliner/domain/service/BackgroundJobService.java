package com.deadliner.domain.service;

import com.deadliner.data.repository.DeadlineRepository;
import com.deadliner.utils.NotifiedStatus;
import com.deadliner.utils.PublishStatus;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@ApplicationScoped
public class BackgroundJobService {
    @Inject DeadlineRepository deadlineRepository;

    @Scheduled(every = "10s")
    @Transactional
    public void notifyDeadline() {
        deadlineRepository.findAll().stream().forEach(deadlineModel -> {
            if (deadlineModel.publishStatus == PublishStatus.PUBLIC
                && (deadlineModel.notifiedStatus == NotifiedStatus.UNNOTIFIED || deadlineModel.notifiedStatus == null)
                && ChronoUnit.HOURS.between(deadlineModel.deadlineDateTime, LocalDateTime.now()) < 24) {
                deadlineModel.notifiedStatus = NotifiedStatus.NOTIFIED;
                var recipientChannel = DiscordService.getInstance()
                                                     .getTextChannelById(deadlineModel.discordChannel.channelId);
                Objects.requireNonNull(recipientChannel)
                       .sendMessage("Deadline " + deadlineModel.label + " is coming!!")
                       .queue();
            }
        });
    }
}
