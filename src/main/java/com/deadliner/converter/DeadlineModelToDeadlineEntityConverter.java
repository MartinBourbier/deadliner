package com.deadliner.converter;

import com.deadliner.data.model.DeadlineModel;
import com.deadliner.domain.entity.DeadlineEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeadlineModelToDeadlineEntityConverter {
    public DeadlineEntity toEntity(DeadlineModel deadlineModel) {
        return new DeadlineEntity()
                .withId(deadlineModel.id)
                .withLabel(deadlineModel.label)
                .withDeadlineDateTime(deadlineModel.deadlineDateTime)
                .withLink(deadlineModel.link)
                .withStatus(deadlineModel.status);
    }
}
