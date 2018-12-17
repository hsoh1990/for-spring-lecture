package com.ex.forblog;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnotherEventHandler {

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public void handle(AppEvent event){
        System.out.println(Thread.currentThread().toString());
        System.out.println("AnotherEventHandler = " +event.getData());
    }
}
