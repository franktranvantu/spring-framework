package com.franktranvantu.jdbc.service;

import com.franktranvantu.jdbc.dao.ActorDao;
import com.franktranvantu.jdbc.domain.Actor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorDao actorDao;

    public int count() {
        return actorDao.count();
    }
    public int count(String firstName) {
        return actorDao.count(firstName);
    }
    public String lastName(long id) {
        return actorDao.lastName(id);
    }
    public Actor actor(long id) {
        return actorDao.actor(id);
    }
    public List<Actor> actors() {
        return actorDao.actors();
    }
    public void insert(String firstName, String lastName) {
        actorDao.insert(firstName, lastName);
    }
    public void update(String lastName, long id) {
        actorDao.update(lastName, id);
    }
    public void delete(long id) {
        actorDao.delete(id);
    }
    public void execute(String sql) {
        actorDao.execute(sql);
    }
}
