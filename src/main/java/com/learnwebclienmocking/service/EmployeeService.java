package com.learnwebclienmocking.service;

import com.learnwebclienmocking.domain.Employee;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class EmployeeService {

    private WebClient webClient;
    public static String PATH_PARAM_BY_ID = "http://localhost:8080/employee/{id}";
    public static String ADD_EMPLOYEE = "http://localhost:8080/employee";

    public EmployeeService(WebClient _webClient) {
        this.webClient = _webClient;
    }

    public Mono<Employee> getEmployeeById(Integer employeeId) {
        return webClient
                .get()
                .uri(PATH_PARAM_BY_ID, employeeId)
                .retrieve()
                .bodyToMono(Employee.class);
    }

    public Mono<Employee> addNewEmployee(Employee newEmployee) {

        return webClient
                .post()
                .uri(ADD_EMPLOYEE)
                .syncBody(newEmployee)
                .retrieve().
                bodyToMono(Employee.class);
    }

    public Mono<Employee> updateEmployee(Integer employeeId, Employee updateEmployee) {

      return  webClient
                .put()
                .uri(PATH_PARAM_BY_ID,employeeId)
                .syncBody(updateEmployee)
                .retrieve()
                .bodyToMono(Employee.class);
    }

    public Mono<String> deleteEmployeeById(Integer employeeId) {
        return webClient
                .delete()
                .uri(PATH_PARAM_BY_ID,employeeId)
                .retrieve()
                .bodyToMono(String.class);
    }
}
