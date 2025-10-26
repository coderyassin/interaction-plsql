package org.yascode.interaction_plsql.service;

import org.yascode.interaction_plsql.entity.Job;

public interface JobService {

    Job getJobById(String jobId);
}
