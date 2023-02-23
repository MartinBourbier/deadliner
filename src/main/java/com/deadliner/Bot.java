package com.deadliner;

import com.deadliner.utils.CustomTextInput;
import lombok.val;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) {
        String token = System.getenv("DISCORD_BOT_TOKEN");
        if (token == null) {
            System.err.println("ERROR: bot token could not be retrieved through env. Did you set it " +
                    "properly?");
            System.exit(1);
        }
        val jda = JDABuilder.createLight(token).addEventListeners(new Bot()).setActivity(Activity.playing(
                "Deadline :panic:")).build();
        jda.updateCommands().addCommands(
                Commands.slash("register", "Registers a new deadline"),
                Commands.slash("remove", "Removes a deadline"),
                Commands.slash("edit", "Edit an already registered deadline")
        ).queue();
    }

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
