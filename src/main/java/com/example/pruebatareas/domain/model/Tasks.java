package com.example.pruebatareas.domain.model;


import com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {


    private Long id;

    @NotNull(message = "El titulo  no puede ser null")
    @NotEmpty(message = "El titulo  no puede estar vacio")
    private String title;

    @NotNull(message = "la fecha no puede ser null")
    private LocalDateTime date;

    private String state;

    private int daysLate;

    private EmployeeEntity employeeEntity;
}
