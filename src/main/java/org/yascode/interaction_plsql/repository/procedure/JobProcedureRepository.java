package org.yascode.interaction_plsql.repository.procedure;

import org.yascode.interaction_plsql.entity.Job;

import java.util.Optional;

public interface JobProcedureRepository {

    Optional<Job> getJob(String jobId);

    Job getJobById(String jobId);
}