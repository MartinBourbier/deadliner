package com.deadliner.presentation.rest;

import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.awt.*;
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
        val embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(label);
        embedBuilder.setColor(Color.orange);
        embedBuilder.addField("Deadline date", date, false);
        embedBuilder.addField("Deadline time", time, false);
        embedBuilder.addField("Deadline link", link, false);
        Button publishButton = Button.success("deadline-publish", "publish");
        Button discardButton = Button.danger("deadline-discard", "discard");
        event.replyEmbeds(embedBuilder.build()).addActionRow(publishButton).addActionRow(discardButton).queue();
    }
}
