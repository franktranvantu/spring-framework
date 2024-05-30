package com.franktranvantu.jdbc.service;

import com.franktranvantu.jdbc.dao.ActorDao;
import com.franktranvantu.jdbc.domain.Actor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorDao actorDao;

    public int getAll() {
        return actorDao.countAll();
    }
    public int getFirstName(String firstName) {
        return actorDao.count(firstName);
    }
    public String getLastName(long id) {
        return actorDao.selectLastName(id);
    }
    public Actor getActor(long id) {
        return actorDao.selectActor(id);
    }
    public List<Actor> getActors() {
        return actorDao.selectActors();
    }
    public void createActor(String firstName, String lastName) {
        actorDao.insert(firstName, lastName);
    }
    public void updateActor(String lastName, long id) {
        actorDao.update(lastName, id);
    }
    public void deleteActor(long id) {
        actorDao.delete(id);
    }
}
