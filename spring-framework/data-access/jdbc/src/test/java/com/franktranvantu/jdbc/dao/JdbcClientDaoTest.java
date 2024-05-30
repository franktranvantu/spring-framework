package com.franktranvantu.jdbc.dao;

import com.franktranvantu.jdbc.TestUtils;
import com.franktranvantu.jdbc.config.AppTestConfig;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {AppTestConfig.class})
@FieldDefaults(level = AccessLevel.PRIVATE)
class JdbcClientDaoTest {
    @Autowired
    JdbcClientDao underTest;
    @Autowired
    DataSource dataSource;

    @BeforeEach
    public void setUp() {
        TestUtils.freshDatabase(dataSource);
    }

    @Test
    void count() {
        final var count = underTest.countAll();
        assertThat(count).isEqualTo(3);
    }

    @Test
    void countFirstName() {
        final var count = underTest.count("User 1");
        assertThat(count).isEqualTo(1);
    }

    @Test
    void lastName() {
        underTest.selectLastName(1);
    }

    @Test
    void actor() {
    }

    @Test
    void actors() {
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