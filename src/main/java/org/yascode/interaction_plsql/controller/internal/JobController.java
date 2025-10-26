package org.yascode.interaction_plsql.controller.internal;

import org.springframework.web.bind.annotation.RestController;
import org.yascode.interaction_plsql.controller.JobApi;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.service.JobService;

@RestController
public class JobController implements JobApi {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public Job getJobById(String id) {
        return jobService.getJobById(id);
    }
}
