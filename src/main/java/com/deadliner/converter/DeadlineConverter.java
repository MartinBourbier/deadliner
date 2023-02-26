package com.deadliner.converter;

import com.deadliner.data.model.DeadlineModel;
import com.deadliner.domain.entity.DeadlineEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeadlineConverter {
    public DeadlineEntity toEntity(DeadlineModel model) {
        return new DeadlineEntity();
    }
}
