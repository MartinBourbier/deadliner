package com.deadliner.converter;

import com.deadliner.data.model.DiscordChannelModel;
import com.deadliner.domain.entity.DiscordChannelEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DiscordChannelModelToDiscordChannelEntityConverter {
    @Inject DeadlineModelToDeadlineEntityConverter deadlineModelToDeadlineEntityConverter;

    public DiscordChannelEntity toEntity(DiscordChannelModel discordChannelModel) {
        return new DiscordChannelEntity()
                .withId(discordChannelModel.id)
                .withChannelId(discordChannelModel.channelId)
                .withDeadlineEntityList(discordChannelModel.deadlineModelList.stream()
                                                                             .map(deadlineModelToDeadlineEntityConverter::toEntity)
                                                                             .toList());
    }
}
