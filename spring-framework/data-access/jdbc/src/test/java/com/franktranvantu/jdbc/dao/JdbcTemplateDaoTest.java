package com.franktranvantu.jdbc.dao;

import com.franktranvantu.jdbc.config.AppTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@ContextConfiguration(classes = {AppTestConfig.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class JdbcTemplateDaoTest {
    @Autowired
    @Qualifier("jdbcTemplateDao")
    private ActorDao underTest;
    @Autowired
    private DataSource dataSource;

    @Test
    void countAll() {
        underTest.countAll();
    }

    @Test
    void count() {
    }

    @Test
    void selectLastName() {
    }

    @Test
    void selectActor() {
    }

    @Test
    void selectActors() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void execute() {
    }
}