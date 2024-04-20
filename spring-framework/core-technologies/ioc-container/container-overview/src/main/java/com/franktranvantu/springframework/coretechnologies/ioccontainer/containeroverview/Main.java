package com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview;

import com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview.model.Employee;
import com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview.model.Team;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("configuration-metadata.xml");
        final var employee = context.getBean("employee", Employee.class);
        final var team = context.getBean("team", Team.class);
        System.out.println(employee);
        System.out.println(team);
    }
}
