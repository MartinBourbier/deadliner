package com.deadliner.presentation.listeners;

import com.deadliner.domain.service.DeadlineService;
import io.quarkus.arc.Arc;
import lombok.val;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CoreListener extends ListenerAdapter {
    DeadlineService deadlineService;

    public CoreListener(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Arc.container().requestContext().activate();
        if (event.getName().equals("register")) {
            deadlineService.replyModal(event);
        } else if (event.getName().equals("remove")) {
            event.reply("Yolo removed!").queue();
        } else if (event.getName().equals("edit")) {
            event.reply("Yolo edited!").queue();
        } else if (event.getName().equals("list")) {
            val deadlineList = deadlineService.getAll();
            StringBuilder res = new StringBuilder();
            for (val deadline : deadlineList) {
                res.append(deadline.toString()).append('\n');
            }
            event.reply(res.toString()).queue();
        }
        Arc.container().requestContext().deactivate();
    }
}
