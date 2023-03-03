package com.deadliner.domain.service;

import com.deadliner.converter.DeadlineEntityToDeadlineModelConverter;
import com.deadliner.converter.DeadlineModelToDeadlineEntityConverter;
import com.deadliner.data.repository.DeadlineRepository;
import com.deadliner.domain.entity.DeadlineEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DeadlineService {
    @Inject
    DeadlineRepository deadlineRepository;

    @Inject DeadlineModelToDeadlineEntityConverter deadlineModelToDeadlineEntityConverter;
    @Inject DeadlineEntityToDeadlineModelConverter deadlineEntityToDeadlineModelConverter;

    @Transactional
    public List<DeadlineEntity> getAll() {
        return deadlineRepository.findAll()
                                 .stream()
                                 .map(model -> deadlineModelToDeadlineEntityConverter.toEntity(model))
                                 .collect(Collectors.toList());
    }

    @Transactional
    public void register(DeadlineEntity deadlineEntity) {
        var model = deadlineEntityToDeadlineModelConverter.toModel(deadlineEntity);
        deadlineRepository.persist(model);
    }
}
