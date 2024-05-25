package com.franktranvantu.jdbc.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActorService {
    JdbcTemplate jdbcTemplate;

    public int count() {
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from t_actor", Integer.class);
        return rowCount;
    }
}
