package com.deadliner.presentation.listeners;

import io.quarkus.arc.Arc;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ButtonListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        Arc.container().requestContext().activate();
        if (Objects.equals(event.getButton().getId(), "deadline-publish")) {
            event.reply("Publishing deadline...").queue();
        } else if (Objects.equals(event.getButton().getId(), "deadline-discard")) {
            event.reply("Discarding deadline...").queue();
        }
        Arc.container().requestContext().deactivate();
    }
}
