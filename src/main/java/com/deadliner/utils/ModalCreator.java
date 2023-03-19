package com.deadliner.utils;

import lombok.val;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class ModalCreator {
    public static Modal of(String id, String title, TextInput... textInputs) {
        val builder = Modal.create(id, title);
        for (val textInput : textInputs) {
            builder.addActionRow(textInput);
        }
        return builder.build();
    }
}
