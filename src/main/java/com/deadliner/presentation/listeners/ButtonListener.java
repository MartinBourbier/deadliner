package com.deadliner.presentation.listeners;

import com.deadliner.domain.entity.DeadlineEntity;
import com.deadliner.domain.service.DeadlineService;
import io.quarkus.arc.Arc;
import lombok.val;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ButtonListener extends ListenerAdapter {
    DeadlineService deadlineService;

    public ButtonListener(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    private static String getTitle(ButtonInteractionEvent event) {
        val embeds = event.getMessage().getEmbeds();
        if (embeds.size() == 0)
            return "<unknown>";
        return embeds.get(0).getTitle();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        Arc.container().requestContext().activate();
        if (Objects.equals(event.getButton().getId(), "deadline-publish")) {
            val label = ButtonListener.getTitle(event);
            event.reply("Publishing deadline " + label + "...").queue();
            deadlineService.publish(new DeadlineEntity().withLabel(label));
        } else if (Objects.equals(event.getButton().getId(), "deadline-discard")) {
            val label = ButtonListener.getTitle(event);
            event.reply("Discarding deadline " + label + "...").queue();
            deadlineService.discard(new DeadlineEntity().withLabel(label));
        }
        Arc.container().requestContext().deactivate();
    }
}
