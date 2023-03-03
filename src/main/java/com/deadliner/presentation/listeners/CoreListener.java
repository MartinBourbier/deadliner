package com.deadliner.presentation.listeners;

import com.deadliner.domain.service.DeadlineService;
import com.deadliner.utils.CustomTextInput;
import com.deadliner.utils.ModalCreator;
import io.quarkus.arc.Arc;
import lombok.val;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class CoreListener extends ListenerAdapter {
    DeadlineService deadlineService;

    public CoreListener(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Arc.container().requestContext().activate();
        if (event.getName().equals("register")) {
            TextInput name = CustomTextInput.of("deadline-label", "Deadline label", TextInputStyle.SHORT,
                                                64, true, "Descriptive label for the deadline.");
            TextInput date = CustomTextInput.of("deadline-date", "Deadline date", TextInputStyle.SHORT,
                                                64, true, "Date of the deadline with format 'mm/dd/yyyy'.");
            TextInput time = CustomTextInput.of("deadline-time", "Deadline time", TextInputStyle.SHORT,
                                                64, true, "Time of the deadline with format 'hh:mm'.");
            TextInput link = CustomTextInput.of("deadline-link", "Deadline link", TextInputStyle.SHORT,
                                                1024, false, "Link to the associated task.");
            Modal modal = ModalCreator.of("deadline-modal", "Deadline info", name, date, time, link);
            event.replyModal(modal).queue();
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
