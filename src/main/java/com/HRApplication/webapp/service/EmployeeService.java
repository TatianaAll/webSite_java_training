package com.hrApplication.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrApplication.webapp.model.Employee;
import com.hrApplication.webapp.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {
    @Autowired
    EmployeeProxy employeeProxy;

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

}
