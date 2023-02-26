package com.deadliner.domain.service;

import com.deadliner.converter.DeadlineConverter;
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

    @Inject
    DeadlineConverter deadlineConverter;

    @Transactional
    public List<DeadlineEntity> getAll() {
        return deadlineRepository.findAll().stream().map(model -> deadlineConverter.toEntity(model)).collect(Collectors.toList());
    }
}
