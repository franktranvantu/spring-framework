package com.franktranvantu.transactionmanagement;

import com.franktranvantu.transactionmanagement.service.PlatformTransactionManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Slf4j
public class Application {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(Application.class);
        final var service = context.getBean(PlatformTransactionManagerService.class);
        service.insertThenUpdate("Frank", "Tran", 20);
    }
}
