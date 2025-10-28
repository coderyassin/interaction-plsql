package org.yascode.interaction_plsql.event.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.event.dto.JobEvent;

import java.time.Clock;

@Service
@Slf4j
public class AddJobPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public AddJobPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(Job job) {

        log.info("Add job: {}", job);

        eventPublisher.publishEvent(new JobEvent(this, Clock.systemDefaultZone(), job));
    }
}
