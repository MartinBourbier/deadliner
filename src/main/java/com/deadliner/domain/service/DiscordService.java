package com.deadliner.domain.service;

import net.dv8tion.jda.api.JDA;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DiscordService {
    private static JDA instance;

    public static JDA getInstance() {
        return DiscordService.instance;
    }

    public static void setInstance(final JDA instance) {
        DiscordService.instance = instance;
    }
}
