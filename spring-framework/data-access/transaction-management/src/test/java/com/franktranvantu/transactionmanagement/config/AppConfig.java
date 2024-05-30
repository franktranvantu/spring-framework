package com.franktranvantu.transactionmanagement.config;

import com.franktranvantu.transactionmanagement.dao.ActorDao;
import com.franktranvantu.transactionmanagement.dao.JdbcClientDao;
import com.franktranvantu.transactionmanagement.mapper.ActorRowMapper;
import com.franktranvantu.transactionmanagement.service.ActorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.PlatformTransactionManager;

import static org.mockito.Mockito.spy;

@Configuration
public class AppConfig {
    @Bean
    public ActorDao actorDao(JdbcClient jdbcClient) {
        return spy(new JdbcClientDao(jdbcClient, new ActorRowMapper()));
    }
    @Bean
    public ActorService actorService(ActorDao actorDao, PlatformTransactionManager transactionManager) {
        return new ActorService(actorDao, transactionManager);
    }
}
