package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.Job;

public interface JobRepository extends JpaRepository<Job, String> {
}
