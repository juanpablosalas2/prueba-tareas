package com.example.pruebatareas.infraestructure.entry_points;


import com.example.pruebatareas.domain.model.Employee;
import com.example.pruebatareas.domain.model.GeneralResponse;
import com.example.pruebatareas.domain.usecase.interfaces.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final GeneralResponse<Employee> generalResponse;
    private final GeneralResponse<Boolean> responseDelete;
    private final EmployeeUseCase employeeUseCase;


    @GetMapping
    public Flux<Employee> getAllAlbums() {
        return employeeUseCase.getAllEmployees();
    }
}
