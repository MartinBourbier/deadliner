package com.deadliner;

import com.deadliner.presentation.rest.CoreCommands;
import com.deadliner.presentation.rest.ModalListener;
import lombok.val;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Main {
    public static void main(String[] args) {
        val token = System.getenv("DISCORD_BOT_TOKEN");
        if (token == null) {
            System.err.println("ERROR: bot token could not be retrieved through env. Did you set it " +
                    "properly?");
            System.exit(1);
        }
        val jda = JDABuilder.createLight(token).addEventListeners(new CoreCommands(), new ModalListener())
                .setActivity(Activity.playing("Deadline :panic:")).build();
        jda.updateCommands().addCommands(
                Commands.slash("register", "Registers a new deadline"),
                Commands.slash("remove", "Removes a deadline"),
                Commands.slash("edit", "Edit an already registered deadline")
        ).queue();
    }
}
