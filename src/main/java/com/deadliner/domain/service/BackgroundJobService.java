package com.deadliner.domain.service;

import com.deadliner.data.repository.DeadlineRepository;
import com.deadliner.utils.NotifiedStatus;
import com.deadliner.utils.PublishStatus;
import io.quarkus.scheduler.Scheduled;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@ApplicationScoped
public class BackgroundJobService {
    @ConfigProperty(name = "discord.deadliner.roles.subscriber") String subscriberRole;

    @Inject DeadlineRepository deadlineRepository;

    @Scheduled(every = "10s")
    @Transactional
    public void notifyDeadline() {
        deadlineRepository.findAll().stream().forEach(deadlineModel -> {
            if (deadlineModel.publishStatus == PublishStatus.PUBLIC
                && (deadlineModel.notifiedStatus == NotifiedStatus.UNNOTIFIED || deadlineModel.notifiedStatus == null)
                && Math.abs(ChronoUnit.HOURS.between(deadlineModel.deadlineDateTime, LocalDateTime.now())) < 24) {
                deadlineModel.notifiedStatus = NotifiedStatus.NOTIFIED;
                var recipientChannel = DiscordService.getInstance()
                                                     .getTextChannelById(deadlineModel.discordChannel.channelId);
                val embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle(deadlineModel.label);
                embedBuilder.setColor(Color.RED);
                embedBuilder.addField("Deadline date/time",
                                      deadlineModel.deadlineDateTime.format(DateTimeFormatter.ofPattern(
                                              "yyyy-MM-dd HH:mm")),
                                      false);
                embedBuilder.addField("Deadline link", deadlineModel.link, false);
                Objects.requireNonNull(recipientChannel)
                       .sendMessage("Deadline is coming!! <@&" + subscriberRole + ">")
                       .addEmbeds(embedBuilder.build())
                       .queue();
            }
        });
    }
}
