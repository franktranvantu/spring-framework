package com.franktranvantu.jdbc;

import com.franktranvantu.jdbc.service.ActorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@AllArgsConstructor
@Slf4j
public class Application {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(Application.class);
        final var service = context.getBean(ActorService.class);
        final var rowCount = service.getAll();
        final var firstName = service.getFirstName("Robert");
        final var lastName = service.getLastName(3);
        final var actor = service.getActor(3);
        final var actors = service.getActors();
        service.createActor("Frank", "Tran");
        service.updateActor("Ruffalo", 3);
        service.deleteActor(3);
        log.info("rowCount: {}", rowCount);
        log.info("firstName: {}", firstName);
        log.info("lastName: {}", lastName);
        log.info("actor: {}", actor);
        log.info("actors: {}", actors);
    }
}
