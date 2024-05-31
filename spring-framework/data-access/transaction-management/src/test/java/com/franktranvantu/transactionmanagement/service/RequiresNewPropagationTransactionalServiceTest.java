package com.franktranvantu.transactionmanagement.service;

import com.franktranvantu.transactionmanagement.TestUtils;
import com.franktranvantu.transactionmanagement.config.AppTestConfig;
import com.franktranvantu.transactionmanagement.dao.ActorDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {AppTestConfig.class})
@ExtendWith(SpringExtension.class)
@EnableTransactionManagement
@ActiveProfiles("test")
class RequiresNewPropagationTransactionalServiceTest {
    @Autowired
    private RequiresNewPropagationTransactionalService underTest;
    @Autowired
    private JdbcClient jdbcClient;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ActorDao actorDao;

    @BeforeEach
    public void setUp() {
        TestUtils.freshDatabase(dataSource);
        reset(actorDao);
    }

    @Test
    void givenInvalidActor_whenInsertThenUpdate_thenRollback() {
        assertThatThrownBy(() -> underTest.insertThenUpdate("First", "Last", 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("You must be at least 18 years old.");
        final var rowCounts = JdbcTestUtils.countRowsInTable(jdbcClient, "t_actor");
        assertThat(rowCounts).isEqualTo(3);
        verify(actorDao).insert("First", "Last");
        verify(actorDao, never()).update("Last", 1);
    }

    @Test
    void givenValidActor_whenInsertThenUpdate_thenCommit() {
        underTest.insertThenUpdate("First", "Last", 20);
        final var rowCounts = JdbcTestUtils.countRowsInTable(jdbcClient, "t_actor");
        assertThatNoException();
        assertThat(rowCounts).isEqualTo(4);
        verify(actorDao).insert("First", "Last");
        verify(actorDao).update("Last", 1);
    }
}