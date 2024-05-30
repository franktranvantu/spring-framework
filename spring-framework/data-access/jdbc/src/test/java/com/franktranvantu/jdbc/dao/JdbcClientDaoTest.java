package com.franktranvantu.jdbc.dao;

import com.franktranvantu.jdbc.TestUtils;
import com.franktranvantu.jdbc.config.AppTestConfig;
import com.franktranvantu.jdbc.domain.Actor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.stream.IntStream;

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
        final var lastName = underTest.selectLastName(1);
        assertThat(lastName).isEqualTo("Tran");
    }

    @Test
    void actor() {
        final var actor = underTest.selectActor(1);
        final var expectActor = new Actor(1L, "User 1", "Tran");
        assertThat(actor).isEqualTo(expectActor);
    }

    @Test
    void actors() {
        final var actors = underTest.selectActors();
        final var expectActors = IntStream
                .range(1, 4)
                .mapToObj(i -> new Actor(Long.valueOf(i), "User %d".formatted(i), "Tran"))
                .toList();
        assertThat(actors).hasSize(3);
        for (int i = 0; i < 3; i++) {
            final var actor = actors.get(i);
            final var expectActor = expectActors.get(i);
            assertThat(actor).isEqualTo(expectActor);
        }
    }

    @Test
    void insert() {
        var count = underTest.countAll();
        assertThat(count).isEqualTo(3);
        underTest.insert("Frank", "Tran");
        count = underTest.countAll();
        assertThat(count).isEqualTo(4);
    }

    @Test
    void update() {
        underTest.update("Nguyen", 1);
        final var actor = underTest.selectActor(1);
        assertThat(actor.id()).isEqualTo(1);
        assertThat(actor.firstName()).isEqualTo("User 1");
        assertThat(actor.lastName()).isEqualTo("Nguyen");
    }

    @Test
    void delete() {
        var count = underTest.countAll();
        assertThat(count).isEqualTo(3);
        underTest.delete(1);
        final var actor = underTest.selectActor(1);
        count = underTest.countAll();
        assertThat(actor).isNull();
        assertThat(count).isEqualTo(2);
    }
}