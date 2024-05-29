package com.franktranvantu.transactionmanagement;

import com.franktranvantu.transactionmanagement.service.ActorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@ComponentScan
@Slf4j
@AllArgsConstructor
public class Application {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(Application.class);
        final var service = context.getBean(ActorService.class);
        service.testTransaction("Frank", "Tran", 20);
    }
}
