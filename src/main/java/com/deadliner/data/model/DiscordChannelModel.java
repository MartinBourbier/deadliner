package com.deadliner.data.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity @Table(name = "discord_channel")
@AllArgsConstructor @NoArgsConstructor @With
public class DiscordChannelModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String channelId;
    @OneToMany(cascade = CascadeType.PERSIST)
    public List<DeadlineModel> deadlineModelList;
}
