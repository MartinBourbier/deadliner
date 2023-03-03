package com.deadliner.data.model;

import com.deadliner.utils.DeadlineStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity @Table(name = "deadline")
@AllArgsConstructor @NoArgsConstructor @With
public class DeadlineModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    public String label;
    public LocalDateTime deadlineDateTime;
    public String link;
    @Enumerated(value = EnumType.STRING)
    public DeadlineStatus status;
}
