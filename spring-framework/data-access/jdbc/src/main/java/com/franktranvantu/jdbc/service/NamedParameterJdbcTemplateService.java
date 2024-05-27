package com.franktranvantu.jdbc.service;

import com.franktranvantu.jdbc.domain.Actor;
import com.franktranvantu.jdbc.mapper.ActorRowMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NamedParameterJdbcTemplateService {
    NamedParameterJdbcTemplate jdbcTemplate;
    ActorRowMapper actorRowMapper;

    public int count() {
        final var namedParameters = Collections.EMPTY_MAP;
        return jdbcTemplate.queryForObject("select count(*) from t_actor", namedParameters, Integer.class);
    }

    public int count(String firstName) {
        final var namedParameters = Map.of("firstName", firstName);
        return jdbcTemplate.queryForObject(
                "select count(*) from t_actor where first_name = :firstName", namedParameters, Integer.class);
    }

    public String lastName(long id) {
        final var namedParameters = Map.of("id", id);
        return this.jdbcTemplate.queryForObject(
                "select last_name from t_actor where id = :id", namedParameters, String.class);
    }

    public Actor actor(long id) {
        final var namedParameters = Map.of("id", id);
        return jdbcTemplate.queryForObject(
                "select id, first_name, last_name from t_actor where id = :id",
                namedParameters,
                actorRowMapper);
    }

    public List<Actor> actors() {
        return jdbcTemplate.query(
                "select id, first_name, last_name from t_actor", actorRowMapper);
    }

    public void insert(String firstName, String lastName) {
        final var namedParameters = Map.of("firstName", firstName, "lastName", lastName);
        jdbcTemplate.update(
                "insert into t_actor (first_name, last_name) values (:firstName, :lastName)", namedParameters);
    }

    public void update(String lastName, long id) {
        final var namedParameters = Map.of("id", id, "lastName", lastName);
        jdbcTemplate.update("update t_actor set last_name = :lastName where id = :id", namedParameters);
    }

    public void delete(long id) {
        final var namedParameters = Map.of("id", id);
        jdbcTemplate.update("delete from t_actor where id = :id", namedParameters);
    }

    public void execute(String sql) {
        final var namedParameters = Collections.EMPTY_MAP;
        jdbcTemplate.update(sql, namedParameters);
    }
}
