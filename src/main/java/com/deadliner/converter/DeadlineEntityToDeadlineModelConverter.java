package com.deadliner.converter;

import com.deadliner.data.model.DeadlineModel;
import com.deadliner.domain.entity.DeadlineEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeadlineEntityToDeadlineModelConverter {
    @Inject DiscordChannelEntityToDiscordChannelModelConverter discordChannelEntityToDiscordChannelModelConverter;

    public DeadlineModel toModel(DeadlineEntity deadlineEntity) {
        return new DeadlineModel()
                .withLabel(deadlineEntity.label)
                .withDeadlineDateTime(deadlineEntity.deadlineDateTime)
                .withLink(deadlineEntity.link)
                .withPublishStatus(deadlineEntity.publishStatus)
                .withNotifiedStatus(deadlineEntity.notifiedStatus)
                .withDiscordChannel(discordChannelEntityToDiscordChannelModelConverter.toModel(deadlineEntity.discordChannelEntity));
    }
}
