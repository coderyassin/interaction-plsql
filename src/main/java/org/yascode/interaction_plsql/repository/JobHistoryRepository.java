package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.JobHistory;
import org.yascode.interaction_plsql.entity.JobHistoryId;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {
}
