package com.deadliner.domain.entity;

import com.deadliner.utils.NotifiedStatus;
import com.deadliner.utils.PublishStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor @With @ToString
public class DeadlineEntity {
    public Long id;
    public String label;
    public LocalDateTime deadlineDateTime;
    public String link;
    public PublishStatus publishStatus;
    public NotifiedStatus notifiedStatus;
    public DiscordChannelEntity discordChannelEntity;
}
