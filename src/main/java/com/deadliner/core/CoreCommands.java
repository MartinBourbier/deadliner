package com.deadliner.core;

import com.deadliner.utils.CustomTextInput;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class CoreCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("register")) {
            TextInput name = CustomTextInput.of("deadline-label", "Deadline label", TextInputStyle.SHORT,
                    64, true, "Descriptive label for the deadline.");
            TextInput date = CustomTextInput.of("deadline-date", "Deadline date", TextInputStyle.SHORT,
                    64, true, "Date of the deadline with format 'mm/dd/yyyy'.");
            TextInput time = CustomTextInput.of("deadline-time", "Deadline time", TextInputStyle.SHORT,
                    64, true, "Time of the deadline with format 'hh:mm'.");
            TextInput link = CustomTextInput.of("deadline-link", "Deadline link", TextInputStyle.SHORT,
                    1024, false, "Link to the associated task.");
            Modal modal = Modal.create("deadline-modal", "Deadline info")
                    .addActionRow(name)
                    .addActionRow(date)
                    .addActionRow(time)
                    .addActionRow(link)
                    .build();
            event.replyModal(modal).queue();
        } else if (event.getName().equals("remove")) {
            event.reply("Yolo removed!").queue();
        } else if (event.getName().equals("edit")) {
            event.reply("Yolo edited!").queue();
        }
    }

}
