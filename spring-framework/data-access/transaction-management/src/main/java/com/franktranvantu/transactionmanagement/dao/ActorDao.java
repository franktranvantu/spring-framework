package com.franktranvantu.transactionmanagement.dao;

import com.franktranvantu.transactionmanagement.domain.Actor;

import java.util.List;

public interface ActorDao {
    int countAll();
    int count(String firstName);
    String selectLastName(long id);
    Actor selectActor(long id);
    List<Actor> selectActors();
    void insert(String firstName, String lastName);
    void update(String lastName, long id);
    void delete(long id);
    void execute(String sql);
}
