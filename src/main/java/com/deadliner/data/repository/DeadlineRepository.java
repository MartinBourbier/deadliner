package com.deadliner.data.repository;

import com.deadliner.data.model.DeadlineModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeadlineRepository implements PanacheRepositoryBase<DeadlineModel, Long> {
}
