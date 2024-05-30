package com.franktranvantu.jdbc.dao;

import com.franktranvantu.jdbc.domain.Actor;
import com.franktranvantu.jdbc.mapper.ActorRowMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JdbcClientDao implements ActorDao {
    JdbcClient jdbcTemplate;
    ActorRowMapper actorRowMapper;

    public int countAll() {
        return jdbcTemplate
                .sql("select count(*) from t_actor")
                .query(Integer.class)
                .single();
    }

    public int count(String firstName) {
        return jdbcTemplate
                .sql("select count(*) from t_actor where first_name = ?")
                .param(firstName)
                .query(Integer.class)
                .single();
    }

    public String selectLastName(long id) {
        return jdbcTemplate
                .sql("select last_name from t_actor where id = :id")
                .param("id", id)
                .query(String.class)
                .single();
    }

    public Actor selectActor(long id) {
        try {
            return jdbcTemplate
                    .sql("select id, first_name, last_name from t_actor where id = :id")
                    .param("id", id)
                    .query(actorRowMapper)
                    .single();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Actor> selectActors() {
        return jdbcTemplate
                .sql("select id, first_name, last_name from t_actor")
                .query(actorRowMapper)
                .list();
    }

    public void insert(String firstName, String lastName) {
        jdbcTemplate
                .sql("insert into t_actor (first_name, last_name) values (:firstName, :lastName)")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .update();
    }

    public void update(String lastName, long id) {
        jdbcTemplate
                .sql("update t_actor set last_name = :lastName where id = :id")
                .param("id", id)
                .param("lastName", lastName)
                .update();
    }

    public void delete(long id) {
        jdbcTemplate
                .sql("delete from t_actor where id = :id")
                .param("id", id)
                .update();
    }

    public void execute(String sql) {
        jdbcTemplate
                .sql(sql)
                .update();
    }
}
