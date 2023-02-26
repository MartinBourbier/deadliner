package com.deadliner.presentation.rest;

import lombok.val;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.Objects;

public class ModalListener extends ListenerAdapter {
    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (!event.getModalId().equals("deadline-modal")) {
            return;
        }

        val label = Objects.requireNonNull(event.getValue("deadline-label")).getAsString();
        val date = Objects.requireNonNull(event.getValue("deadline-date")).getAsString();
        val time = Objects.requireNonNull(event.getValue("deadline-time")).getAsString();
        var link = "None";
        ModalMapping linkValue;
        if ((linkValue = event.getValue("deadline-link")) != null) {
            link = linkValue.getAsString();
        }
        event.reply(String.format("Created new event with label '%s', end date '%s', end time '%s' and link" +
                " '%s'", label, date, time, link)).queue();
    }
}
