package com.hrApplication.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hrApplication.webapp.CustomProperties;
import com.hrApplication.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
    @Autowired
    private CustomProperties properties;

    // public Employee getEmployee(final int id) {
    //     String baseApiUrl = properties.getApiUrl();
    //     String getEmployeeUrl = baseApiUrl + "/employee/" + id; 

    //     RestTemplate restTemplate = new RestTemplate();
    //     ResponseEntity<Employee> response = restTemplate.exchange(
    //         getEmployeeUrl, 
    //         HttpMethod.GET, 
    //         null, 
    //         Employee.class
    //         );

    //     log.debug("Get Employee call " + response.getStatusCode().toString());
    //     return response.getBody();
    // }

    public Iterable<Employee> getEmployees() {
        // Catch API's URL
        String baseApiUrl = properties.getApiUrl();
        // Completing the URL wih the path of the endpoint
        String getEmployeeUrl = baseApiUrl + "/employees"; 
        
        // new instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // call method exchange, giving it URL, HTTP Method, return type
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
            getEmployeeUrl, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<Iterable<Employee>>() {} 
            );

        log.debug("Get Employee call " + response.getStatusCode().toString());
        return response.getBody();
    }

    public Employee createEmployee(Employee newEmployee) {
        String baseApiUrl = properties.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(newEmployee);
        ResponseEntity<Employee> response = restTemplate.exchange(
            createEmployeeUrl,
            HttpMethod.POST,
            request,
            Employee.class);
        log.debug("create employee call " + response.getStatusCode().toString());
        return response.getBody();
    }
}
