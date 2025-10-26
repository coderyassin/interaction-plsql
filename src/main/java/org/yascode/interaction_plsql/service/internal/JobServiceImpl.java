package org.yascode.interaction_plsql.service.internal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.repository.procedure.JobProcedureRepository;
import org.yascode.interaction_plsql.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    private final JobProcedureRepository jobProcedureRepository;

    public JobServiceImpl(JobProcedureRepository jobProcedureRepository) {
        this.jobProcedureRepository = jobProcedureRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Job getJobById(String jobId) {
        return jobProcedureRepository.getJobById(jobId);
    }
}
