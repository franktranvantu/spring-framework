package com.franktranvantu.jdbc;

import com.franktranvantu.jdbc.service.JdbcClientService;
import com.franktranvantu.jdbc.service.JdbcTemplateService;
import com.franktranvantu.jdbc.service.NamedParameterJdbcTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Slf4j
public class Application {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(Application.class);
//        final var service = context.getBean(JdbcTemplateService.class);
        final var service = context.getBean(NamedParameterJdbcTemplateService.class);
//        final var service = context.getBean(JdbcClientService.class);
        final var rowCount = service.count();
        final var countOfActorsNamedJoe = service.count("Robert");
        final var lastName = service.lastName(3);
        final var actor = service.actor(3);
        final var actors = service.actors();
        service.insert("Frank", "Tran");
        service.update("Ruffalo", 3);
        service.delete(3);
        service.execute("create table mytable (id integer, name varchar(100))");
        log.info("rowCount: {}", rowCount);
        log.info("countOfActorsNamedJoe: {}", countOfActorsNamedJoe);
        log.info("lastName: {}", lastName);
        log.info("actor: {}", actor);
        log.info("actors: {}", actors);
    }
}
