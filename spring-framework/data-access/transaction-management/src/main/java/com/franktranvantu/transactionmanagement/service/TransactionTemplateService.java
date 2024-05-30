package com.franktranvantu.transactionmanagement.service;

import com.franktranvantu.transactionmanagement.dao.ActorDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TransactionTemplateService {
    ActorDao actorDao;
    TransactionTemplate transactionTemplate;

    public void insertThenUpdate(String firstName, String lastName, int age) {
        transactionTemplate.execute(status -> {
            actorDao.insert(firstName, lastName);
            if (age < 18) {
                throw new RuntimeException("You must be at least 18 years old.");
            }
            actorDao.update(lastName, 1);
            return null;
        });
    }

    public int insertThenUpdateAndReturn(String firstName, String lastName, int age) {
        final var id = 1;
        return transactionTemplate.execute(status -> {
            actorDao.insert(firstName, lastName);
            if (age < 18) {
                throw new RuntimeException("You must be at least 18 years old.");
            }
            actorDao.update(lastName, id);
            return id;
        });
    }
}
