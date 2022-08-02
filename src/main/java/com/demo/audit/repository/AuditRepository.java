package com.demo.audit.repository;

import com.demo.audit.model.Audit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditRepository implements PanacheRepository<Audit> {
}
