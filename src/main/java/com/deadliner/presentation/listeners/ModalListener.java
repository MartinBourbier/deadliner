package com.deadliner.presentation.listeners;

import com.deadliner.domain.entity.DeadlineEntity;
import com.deadliner.domain.service.DeadlineService;
import com.deadliner.utils.DeadlineStatus;
import io.quarkus.arc.Arc;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ModalListener extends ListenerAdapter {
    DeadlineService deadlineService;

    public ModalListener(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        Arc.container().requestContext().activate();
        if (!event.getModalId().equals("deadline-modal")) {
            Arc.container().requestContext().deactivate();
            return;
        }

        val label = Objects.requireNonNull(event.getValue("deadline-label")).getAsString();
        val date = Objects.requireNonNull(event.getValue("deadline-date")).getAsString();
        val time = Objects.requireNonNull(event.getValue("deadline-time")).getAsString();
        val link = "toto.com";
        val embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(label);
        embedBuilder.setColor(Color.orange);
        embedBuilder.addField("Deadline date", date, false);
        embedBuilder.addField("Deadline time", time, false);
        embedBuilder.addField("Deadline link", link, false);
        Button publishButton = Button.success("deadline-publish", "publish");
        Button discardButton = Button.danger("deadline-discard", "discard");
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        var localDateTime = LocalDateTime.parse(String.format("%s %s", date, time), formatter);
        deadlineService.register(new DeadlineEntity().withLabel(label)
                                                     .withDeadlineDateTime(localDateTime)
                                                     .withLink(link)
                                                     .withStatus(DeadlineStatus.PRIVATE));
        event.replyEmbeds(embedBuilder.build()).addActionRow(publishButton).addActionRow(discardButton).queue();
        Arc.container().requestContext().deactivate();
    }
}
