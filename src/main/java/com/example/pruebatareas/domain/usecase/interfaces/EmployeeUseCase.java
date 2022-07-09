package com.example.pruebatareas.domain.usecase.interfaces;

import com.example.pruebatareas.domain.model.Employee;
import reactor.core.publisher.Flux;


public interface EmployeeUseCase {

    Flux<Employee> getAllEmployees();
}
