package com.deadliner.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @With @ToString
public class DiscordChannelEntity {
    public Long id;
    public String channelId;
    public List<DeadlineEntity> deadlineEntityList;
}
