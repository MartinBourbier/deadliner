package com.deadliner;

import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) {
        String token = System.getenv("DISCORD_BOT_TOKEN");
        if (token == null) {
            System.err.println("ERROR: bot token could not be retrieved through env. Did you set it properly?");
            System.exit(1);
        }
        JDABuilder jdaBuilder = JDABuilder.createLight(token);
        jdaBuilder.build();
    }
}