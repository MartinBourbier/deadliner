package com.deadliner;

import lombok.val;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

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
        // make sure we handle the right command
        if (event.getName().equals("register")) {
            event.reply("Yolo registered!").queue();
        }
        else if (event.getName().equals("remove")) {
            event.reply("Yolo removed!").queue();
        }
        else if (event.getName().equals("edit")) {
            event.reply("Yolo edited!").queue();
        }
    }
}
