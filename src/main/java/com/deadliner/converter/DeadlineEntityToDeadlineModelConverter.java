package com.deadliner.converter;

import com.deadliner.data.model.DeadlineModel;
import com.deadliner.domain.entity.DeadlineEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeadlineEntityToDeadlineModelConverter {
    public DeadlineModel toModel(DeadlineEntity deadlineEntity) {
        return new DeadlineModel()
                .withLabel(deadlineEntity.label)
                .withDeadlineDateTime(deadlineEntity.deadlineDateTime)
                .withLink(deadlineEntity.link)
                .withStatus(deadlineEntity.status);
    }
}
