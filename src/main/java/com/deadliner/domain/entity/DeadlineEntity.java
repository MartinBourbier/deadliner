package com.deadliner.domain.entity;

import com.deadliner.utils.DeadlineStatus;
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
    public DeadlineStatus status;
}
