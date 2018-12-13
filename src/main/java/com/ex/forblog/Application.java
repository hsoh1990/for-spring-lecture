package com.ex.forblog;

import com.ex.out.OutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class Application {
    @Autowired
    OutService outService;

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        SpringApplication app = new SpringApplication(Application.class);
        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
            ctx.registerBean(OutService.class);
            ctx.registerBean(ApplicationRunner.class,
                    () -> args1 -> System.out.println("Functional Bean Definition!"));
        });
        app.run(args);
    }

}
