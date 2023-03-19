package com.deadliner.utils;

import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class CustomModal {
    public static Modal getModal() {
        TextInput name = CustomTextInput.of("deadline-label", "Deadline label", TextInputStyle.SHORT,
                                            64, true, "Descriptive label for the deadline.");
        TextInput date = CustomTextInput.of("deadline-date", "Deadline date", TextInputStyle.SHORT,
                                            64, true, "Date of the deadline with format 'mm/dd/yyyy'.");
        TextInput time = CustomTextInput.of("deadline-time", "Deadline time", TextInputStyle.SHORT,
                                            64, true, "Time of the deadline with format 'hh:mm'.");
        TextInput link = CustomTextInput.of("deadline-link", "Deadline link", TextInputStyle.SHORT,
                                            1024, false, "Link to the associated task.");
        TextInput channelId = CustomTextInput.of("deadline-discord-channel", "Discord channel id", TextInputStyle.SHORT,
                                                 1024, false, "Discord notification channel id.");
        return ModalCreator.of("deadline-modal", "Deadline info", name, date, time, link, channelId);
    }
}
