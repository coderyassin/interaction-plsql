package org.yascode.interaction_plsql.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.event.dto.JobEvent;
import org.yascode.interaction_plsql.repository.JobRepository;

@Component
@Async
@Slf4j
public class AddJobListener {

    private final JobRepository jobRepository;

    public AddJobListener(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @EventListener
    public void handleAddJobEvent(JobEvent jobEvent) {
        Job job = jobEvent.getJob();
        log.info("Listener received job {}: ", job);

        jobRepository.addJob(job.getJobId(), job.getJobTitle(), job.getMinSalary(), job.getMaxSalary());
    }
}
