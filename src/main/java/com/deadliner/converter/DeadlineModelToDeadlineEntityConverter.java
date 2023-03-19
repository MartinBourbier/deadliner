package com.deadliner.converter;

import com.deadliner.data.model.DeadlineModel;
import com.deadliner.domain.entity.DeadlineEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeadlineModelToDeadlineEntityConverter {
    @Inject DiscordChannelModelToDiscordChannelEntityConverter discordChannelModelToDiscordChannelEntityConverter;

    public DeadlineEntity toEntity(DeadlineModel deadlineModel) {
        return new DeadlineEntity()
                .withId(deadlineModel.id)
                .withLabel(deadlineModel.label)
                .withDeadlineDateTime(deadlineModel.deadlineDateTime)
                .withLink(deadlineModel.link)
                .withPublishStatus(deadlineModel.publishStatus)
                .withNotifiedStatus(deadlineModel.notifiedStatus)
                .withDiscordChannelEntity(discordChannelModelToDiscordChannelEntityConverter.toEntity(deadlineModel.discordChannel));
    }
}
