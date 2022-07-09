package com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities;


import com.example.pruebatareas.domain.model.Employee;
import com.example.pruebatareas.domain.model.Tasks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "employee")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "employeeEntity",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    List<TaskEntity> taskEntities;



    public static EmployeeEntity convertToEntity(Employee employee) {
        return EmployeeEntity.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    public static Employee convertToModel(EmployeeEntity employeeEntity) {
        return Employee.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .email(employeeEntity.getEmail())
                .build();
    }

}
