package com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {
    private Main underTest = new Main();

    @Test
    void testGetEmployee() {
        final var employee = underTest.getEmployee();
        assertThat(employee.getFirstName()).isEqualTo("Tu");
        assertThat(employee.getLastName()).isEqualTo("Tran Van");
        assertThat(employee.getPhoneNumber()).isEqualTo("0123456789");
    }

    @Test
    void testGetTeam() {
        final var team = underTest.getTeam();
        assertThat(team.getName()).isEqualTo("Print Engine");
        assertThat(team.getLocation()).isEqualTo("Ho Chi Minh");
    }
}