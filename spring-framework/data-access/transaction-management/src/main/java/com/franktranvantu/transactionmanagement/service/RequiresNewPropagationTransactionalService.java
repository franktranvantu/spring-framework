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
public class RequiresNewPropagationTransactionalService {
    ActorDao actorDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertThenUpdate(String firstName, String lastName, int age) {
        insert(firstName, lastName);
        if (age < 18) {
            throw new RuntimeException("You must be at least 18 years old.");
        }
        update(lastName, 1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(String firstName, String lastName) {
        actorDao.insert(firstName, lastName);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(String lastName, int id) {
        actorDao.update(lastName, 1);
    }
}
