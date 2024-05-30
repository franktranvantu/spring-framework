package com.franktranvantu.jdbc.config;

import com.franktranvantu.jdbc.dao.JdbcClientDao;
import com.franktranvantu.jdbc.dao.JdbcTemplateDao;
import com.franktranvantu.jdbc.mapper.ActorRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
@Import({DatasourceTestConfig.class, JdbcOperationsTestConfig.class, JdbcClientTestConfig.class})
public class AppTestConfig {
    private static final ActorRowMapper ACTOR_ROW_MAPPER = new ActorRowMapper();
    @Bean
    public JdbcClientDao jdbcClientDao(JdbcClient jdbcTemplate) {
        return new JdbcClientDao(jdbcTemplate, ACTOR_ROW_MAPPER);
    }

    @Bean
    public JdbcTemplateDao jdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        return new JdbcTemplateDao(jdbcTemplate, ACTOR_ROW_MAPPER);
    }
}
