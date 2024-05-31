package com.franktranvantu.transactionmanagement.config;

import com.franktranvantu.transactionmanagement.dao.ActorDao;
import com.franktranvantu.transactionmanagement.dao.JdbcClientDao;
import com.franktranvantu.transactionmanagement.mapper.ActorRowMapper;
import com.franktranvantu.transactionmanagement.service.PlatformTransactionManagerService;
import com.franktranvantu.transactionmanagement.service.RequiresNewPropagationTransactionalService;
import com.franktranvantu.transactionmanagement.service.TransactionTemplateService;
import com.franktranvantu.transactionmanagement.service.RequiredPropagationTransactionalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.mockito.Mockito.spy;

@Configuration
@Import({DatasourceTestConfig.class, JdbcClientTestConfig.class, TransactionTestConfig.class})
public class AppTestConfig {
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

    @Bean
    public RequiredPropagationTransactionalService requiredPropagationTransactionalService(ActorDao actorDao) {
        return new RequiredPropagationTransactionalService(actorDao);
    }

    @Bean
    public RequiresNewPropagationTransactionalService requiresNewPropagationTransactionalService(ActorDao actorDao) {
        return new RequiresNewPropagationTransactionalService(actorDao);
    }
}
