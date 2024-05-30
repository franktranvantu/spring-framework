package com.franktranvantu.jdbc.config;

import com.franktranvantu.jdbc.dao.JdbcClientDao;
import com.franktranvantu.jdbc.mapper.ActorRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
@Import({DatasourceTestConfig.class, JdbcOperationsTestConfig.class, JdbcClientTestConfig.class})
public class AppTestConfig {
    @Bean
    public JdbcClientDao jdbcClientDao(JdbcClient jdbcTemplate) {
        return new JdbcClientDao(jdbcTemplate, new ActorRowMapper());
    }
}
