package com.frank.springframework;

import com.frank.springframework.services.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "dao.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        EmployeeService service = context.getBean("employeeService", EmployeeService.class);
        List<String> employeeList = service.getEmployeeList();
    }
}
