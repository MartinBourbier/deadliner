package com.deadliner.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeadlineModel extends PanacheEntityBase {
    @Id public Long id;
}
