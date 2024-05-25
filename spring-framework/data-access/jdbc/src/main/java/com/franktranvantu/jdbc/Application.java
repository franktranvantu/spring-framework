package com.franktranvantu.jdbc;

import com.franktranvantu.jdbc.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Slf4j
public class Application {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(Application.class);
        final var service = context.getBean(ActorService.class);
        int count = service.count();
        log.info("Total actors: {}", count);
    }
}
