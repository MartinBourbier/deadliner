package com.deadliner.domain.service;

import com.deadliner.presentation.listeners.ButtonListener;
import com.deadliner.presentation.listeners.CoreListener;
import com.deadliner.presentation.listeners.ModalListener;
import io.quarkus.runtime.Startup;
import lombok.val;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Startup
public class StartupService {
    @Inject DeadlineService deadlineService;

    @ConfigProperty(name = "discord.bot.token")
    String botToken;

    @PostConstruct
    void postConstruct() {
        System.out.println("Starting up...");
        val jda = JDABuilder.createLight(botToken);
        jda.addEventListeners(new CoreListener(deadlineService),
                              new ModalListener(deadlineService),
                              new ButtonListener());
        jda.setActivity(Activity.playing("Deadline :panic:"));
        DiscordService.setInstance(jda.build());
    }
}
