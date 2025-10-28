package org.yascode.interaction_plsql.event.dto;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomEvent extends ApplicationEvent {

    private String message;

    public CustomEvent(Object source) {
        super(source);
    }

    public CustomEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public CustomEvent(Object source, Clock clock, String message) {
        this(source, clock);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
