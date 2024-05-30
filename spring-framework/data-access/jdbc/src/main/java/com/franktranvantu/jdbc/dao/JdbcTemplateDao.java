package com.franktranvantu.jdbc.dao;

import com.franktranvantu.jdbc.domain.Actor;
import com.franktranvantu.jdbc.mapper.ActorRowMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JdbcTemplateDao implements ActorDao {
    JdbcTemplate jdbcTemplate;
    ActorRowMapper actorRowMapper;

    public int countAll() {
        return jdbcTemplate.queryForObject("select count(*) from t_actor", Integer.class);
    }

    public int count(String firstName) {
        return jdbcTemplate.queryForObject(
                "select count(*) from t_actor where first_name = ?", Integer.class, firstName);
    }

    public String selectLastName(long id) {
        return this.jdbcTemplate.queryForObject(
                "select last_name from t_actor where id = ?",
                String.class, id);
    }

    public Actor selectActor(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select id, first_name, last_name from t_actor where id = ?",
                    (resultSet, rowNum) -> new Actor(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    ),
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Actor> selectActors() {
        return jdbcTemplate.query(
                "select id, first_name, last_name from t_actor", actorRowMapper);
    }

    public void insert(String firstName, String lastName) {
        jdbcTemplate.update(
                "insert into t_actor (first_name, last_name) values (?, ?)", firstName, lastName);
    }

    public void update(String lastName, long id) {
        jdbcTemplate.update("update t_actor set last_name = ? where id = ?", lastName, id);
    }

    public void delete(long id) {
        jdbcTemplate.update("delete from t_actor where id = ?", id);
    }

    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }
}
