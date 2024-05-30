package com.franktranvantu.transactionmanagement.config;

import com.franktranvantu.transactionmanagement.dao.ActorDao;
import com.franktranvantu.transactionmanagement.dao.JdbcClientDao;
import com.franktranvantu.transactionmanagement.mapper.ActorRowMapper;
import com.franktranvantu.transactionmanagement.service.PlatformTransactionManagerService;
import com.franktranvantu.transactionmanagement.service.TransactionTemplateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.mockito.Mockito.spy;

@Configuration
public class AppConfig {
    @Bean
    public ActorDao actorDao(JdbcClient jdbcClient) {
        return spy(new JdbcClientDao(jdbcClient, new ActorRowMapper()));
    }
    @Bean
    public PlatformTransactionManagerService platformTransactionManagerService(ActorDao actorDao, PlatformTransactionManager transactionManager) {
        return new PlatformTransactionManagerService(actorDao, transactionManager);
    }

    @Bean
    public TransactionTemplateService transactionTemplateService(ActorDao actorDao, TransactionTemplate transactionTemplate) {
        return new TransactionTemplateService(actorDao, transactionTemplate);
    }
}
