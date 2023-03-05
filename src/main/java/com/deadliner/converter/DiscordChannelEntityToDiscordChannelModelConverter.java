package com.deadliner.converter;

import com.deadliner.data.model.DiscordChannelModel;
import com.deadliner.domain.entity.DiscordChannelEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DiscordChannelEntityToDiscordChannelModelConverter {
    @Inject DeadlineEntityToDeadlineModelConverter deadlineEntityToDeadlineModelConverter;

    public DiscordChannelModel toModel(DiscordChannelEntity discordChannelEntity) {
        var discordChannelModel = new DiscordChannelModel()
                .withChannelId(discordChannelEntity.channelId);
        if (discordChannelEntity.deadlineEntityList == null) {
            return discordChannelModel;
        }
        discordChannelModel = discordChannelModel
                .withDeadlineModelList(discordChannelEntity.deadlineEntityList.stream()
                                                                              .map(deadlineEntityToDeadlineModelConverter::toModel)
                                                                              .toList());
        return discordChannelModel;
    }
}
