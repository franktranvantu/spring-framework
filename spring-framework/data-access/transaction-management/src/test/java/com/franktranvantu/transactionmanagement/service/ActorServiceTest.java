package com.franktranvantu.transactionmanagement.service;

import com.franktranvantu.transactionmanagement.config.AppConfig;
import com.franktranvantu.transactionmanagement.config.DatasourceConfig;
import com.franktranvantu.transactionmanagement.config.JdbcClientConfig;
import com.franktranvantu.transactionmanagement.config.TransactionConfig;
import com.franktranvantu.transactionmanagement.dao.ActorDao;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {AppConfig.class, DatasourceConfig.class, JdbcClientConfig.class, TransactionConfig.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class ActorServiceTest {
    @Autowired
    ActorService underTest;
    @Autowired
    JdbcClient jdbcClient;
    @Autowired
    ActorDao actorDao;

    @BeforeEach
    public void setUp() {
        reset(actorDao);
    }

    @Test
    void givenInvalidActor_whenUsingPlatformTransactionManager_thenRollback() {
        assertThatThrownBy(() -> underTest.insertThenUpdate("First", "Last", 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("You must be at least 18 years old.");
        final var rowCounts = JdbcTestUtils.countRowsInTable(jdbcClient, "t_actor");
        assertThat(rowCounts).isEqualTo(3);
        verify(actorDao).insert("First", "Last");
        verify(actorDao, never()).update("Last", 1);
    }

    @Test
    void givenValidActor_whenUsingPlatformTransactionManager_thenCommit() {
        underTest.insertThenUpdate("First", "Last", 20);
        assertThatNoException();
        final var rowCounts = JdbcTestUtils.countRowsInTable(jdbcClient, "t_actor");
        assertThat(rowCounts).isEqualTo(4);
        verify(actorDao).insert("First", "Last");
        verify(actorDao).update("Last", 1);
    }
}