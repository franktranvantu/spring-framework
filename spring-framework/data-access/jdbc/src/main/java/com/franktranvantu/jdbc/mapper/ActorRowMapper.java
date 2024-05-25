package com.franktranvantu.jdbc.mapper;

import com.franktranvantu.jdbc.domain.Actor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActorRowMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Actor(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
    }
}
