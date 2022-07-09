package com.example.pruebatareas.domain.model;

import com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;

    @NotNull(message = "el name no puede ser null")
    @NotEmpty(message = "el name no puede estar vacio")
    private String name;

    @NotNull(message = "el email no puede ser null")
    @NotEmpty(message = "el email no puede estar vacio")
    private String email;

    List<TaskEntity> taskEntities;
}
