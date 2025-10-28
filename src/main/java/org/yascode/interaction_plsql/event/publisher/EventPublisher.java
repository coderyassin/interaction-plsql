package org.yascode.interaction_plsql.event.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.yascode.interaction_plsql.event.dto.CustomEvent;

import java.time.Clock;

@Service
@Slf4j
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(String data) {

        log.info("Service processing data: {}", data);

        eventPublisher.publishEvent(new CustomEvent(this, Clock.systemDefaultZone(), data));
    }

}

/*
* Event --> ApplicationEvent
* Publisher ---> ApplicationEventPublisher
* Listener  --> EventListener
* */
