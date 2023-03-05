package com.deadliner.domain.service;

import com.deadliner.converter.DeadlineEntityToDeadlineModelConverter;
import com.deadliner.converter.DeadlineModelToDeadlineEntityConverter;
import com.deadliner.data.repository.DeadlineRepository;
import com.deadliner.domain.entity.DeadlineEntity;
import com.deadliner.utils.CustomModal;
import com.deadliner.utils.DeadlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class DeadlineService {
    @ConfigProperty(name = "discord.deadliner.role") String deadlinerRole;
    @ConfigProperty(name = "discord.deadliner.channel") String deadlinerChannel;

    @Inject
    DeadlineRepository deadlineRepository;

    @Inject DeadlineModelToDeadlineEntityConverter deadlineModelToDeadlineEntityConverter;
    @Inject DeadlineEntityToDeadlineModelConverter deadlineEntityToDeadlineModelConverter;

    @Transactional
    public List<DeadlineEntity> getAll() {
        return deadlineRepository.findAll()
                                 .stream()
                                 .map(model -> deadlineModelToDeadlineEntityConverter.toEntity(model))
                                 .collect(Collectors.toList());
    }

    @Transactional
    public void register(DeadlineEntity deadlineEntity) {
        var model = deadlineEntityToDeadlineModelConverter.toModel(deadlineEntity);
        deadlineRepository.persist(model);
    }

    @Transactional
    public void publish(DeadlineEntity deadlineEntity) {
        var deadline = deadlineRepository.find("label = ?1", deadlineEntity.label).firstResult();
        deadline.status = DeadlineStatus.PUBLIC;
    }

    private boolean isAuthorized(Member member) {
        return member.getRoles().stream().anyMatch(perm -> perm.getId().equals(deadlinerRole));
    }

    public void replyModal(SlashCommandInteractionEvent event) {
        if (!isAuthorized(Objects.requireNonNull(event.getMember())))
            event.reply("You cannot do that little chenapan!").queue();
        if (!event.getChannel().getId().equals(deadlinerChannel))
            event.reply(String.format("You must submit this command in the <#%s> channel!", deadlinerChannel)).queue();
        event.replyModal(CustomModal.getModal()).queue();
    }
}
