package com.franktranvantu.transactionmanagement.dao;

import com.franktranvantu.transactionmanagement.domain.Actor;

import java.util.List;

public interface ActorDao {
    int count();
    int count(String firstName);
    String lastName(long id);
    Actor actor(long id);
    List<Actor> actors();
    void insert(String firstName, String lastName);
    void update(String lastName, long id);
    void delete(long id);
    void execute(String sql);
}
