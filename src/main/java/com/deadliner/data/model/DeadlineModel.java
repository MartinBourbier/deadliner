package com.deadliner.data.model;

import com.deadliner.utils.NotifiedStatus;
import com.deadliner.utils.PublishStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity @Table(name = "deadline")
@AllArgsConstructor @NoArgsConstructor @With @ToString
public class DeadlineModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public String label;
    public LocalDateTime deadlineDateTime;
    public String link;
    @Enumerated(value = EnumType.STRING)
    public PublishStatus publishStatus;
    @Enumerated(value = EnumType.STRING)
    public NotifiedStatus notifiedStatus;

    @OneToOne(cascade = CascadeType.PERSIST)
    public DiscordChannelModel discordChannel;
}
