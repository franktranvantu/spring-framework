package com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview;

import com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview.model.Employee;
import com.franktranvantu.springframework.coretechnologies.ioccontainer.containeroverview.model.Team;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("configuration-metadata.xml");

    public Employee getEmployee() {
        return context.getBean("employee", Employee.class);
    }

    public Team getTeam() {
        return context.getBean("team", Team.class);
    }
}
