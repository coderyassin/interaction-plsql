package org.yascode.interaction_plsql.event.dto;

import org.springframework.context.ApplicationEvent;
import org.yascode.interaction_plsql.entity.Job;

import java.time.Clock;

public class JobEvent extends ApplicationEvent {

    private Job job;

    public JobEvent(Object source) {
        super(source);
    }

    public JobEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public JobEvent(Object source, Clock clock, Job job) {
        this(source, clock);
        this.job = job;
    }

    public Job getJob() {
        return this.job;
    }
}
