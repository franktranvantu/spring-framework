package com.frank.springframework.services;

import com.frank.springframework.dao.EmployeeDao;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();

    @Override
    public List<String> getEmployeeList() {
        return employeeDao.getEmployeeList();
    }
}
