package org.yascode.interaction_plsql.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.yascode.interaction_plsql.event.dto.CustomEvent;

@Component
@Async
@Slf4j
public class CustomEventListener {

    @EventListener
    public void handleCustomEvent(CustomEvent event) {
        log.info("Listener received custom event: {}", event.getMessage());
    }
}
