package com.example.pruebatareas.domain.usecase;

import com.example.pruebatareas.domain.model.Employee;
import com.example.pruebatareas.domain.model.exceptions.EmployeeException;
import com.example.pruebatareas.domain.usecase.interfaces.EmployeeUseCase;
import com.example.pruebatareas.infraestructure.driver_adapter.repositories.EmployeeRepository;
import com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeUseCaseImpl implements EmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public Flux<Employee> getAllEmployees() {
        return Flux.fromIterable(employeeRepository.findAll())
                .map(EmployeeEntity::convertToModel)
                .onErrorResume(throwable -> Mono.error(new EmployeeException("Employee could not be founded",throwable)));
    }
}
