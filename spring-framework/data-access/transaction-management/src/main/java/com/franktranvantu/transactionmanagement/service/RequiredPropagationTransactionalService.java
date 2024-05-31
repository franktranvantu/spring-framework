package com.franktranvantu.transactionmanagement.service;

import com.franktranvantu.transactionmanagement.dao.ActorDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequiredPropagationTransactionalService {
    ActorDao actorDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertThenUpdate(String firstName, String lastName, int age) {
        insert(firstName, lastName);
        if (age < 18) {
            throw new RuntimeException("You must be at least 18 years old.");
        }
        update(lastName, 1);
    }

    @Transactional
    public void insert(String firstName, String lastName) {
        actorDao.insert(firstName, lastName);
    }

    @Transactional
    public void update(String lastName, int id) {
        actorDao.update(lastName, 1);
    }
}
