package com.frank.springframework.dao;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public List<String> getEmployeeList() {
        List<String> employeeList = new ArrayList<>();
        employeeList.add("Frank");
        employeeList.add("Patrik");
        employeeList.add("Jeff");
        return employeeList;
    }
}
