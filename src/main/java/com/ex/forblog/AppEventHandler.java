package com.ex.forblog;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppEventHandler {

    @EventListener
    public void handle(AppEvent event){
        System.out.println(event.getData());
    }
}
