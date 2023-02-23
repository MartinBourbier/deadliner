package com.deadliner.utils;

import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class CustomTextInput {
    public static TextInput of(String label, String description, TextInputStyle style, int maxLength, boolean required, String placeHolder) {
        return TextInput.create(label, description, style)
                .setMaxLength(maxLength)
                .setRequired(required)
                .setPlaceholder(placeHolder)
                .build();
    }
}
