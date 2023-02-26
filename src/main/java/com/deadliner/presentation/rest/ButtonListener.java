package com.deadliner.presentation.rest;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ButtonListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (Objects.equals(event.getButton().getId(), "deadline-publish")) {
            event.reply("Publishing deadline...").queue();
        }
        else if (Objects.equals(event.getButton().getId(), "deadline-discard")) {
            event.reply("Discarding deadline...").queue();
        }
    }
}
