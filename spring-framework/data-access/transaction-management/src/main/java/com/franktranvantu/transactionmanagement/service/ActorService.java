package com.franktranvantu.transactionmanagement.service;

import com.franktranvantu.transactionmanagement.dao.ActorDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ActorService {
    ActorDao actorDao;
    PlatformTransactionManager transactionManager;

    public void testTransaction(String firstName, String lastName, int age) {
        final var def = new DefaultTransactionDefinition();
        final var status = transactionManager.getTransaction(def);
        try {
            actorDao.insert(firstName, lastName);
            if (age < 18) {
                throw new RuntimeException("You must be at least 18 years old.");
            }
            actorDao.update(lastName, 3);
            transactionManager.commit(status);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            transactionManager.rollback(status);
        }
    }
}
