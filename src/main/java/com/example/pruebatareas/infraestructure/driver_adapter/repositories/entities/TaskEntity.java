package com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities;


import com.example.pruebatareas.domain.model.Tasks;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "task")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String state;

    private int daysLate;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;


    public static TaskEntity convertToEntity(Tasks tasks) {
        return TaskEntity.builder()
                .id(tasks.getId())
                .title(tasks.getTitle())
                .date(tasks.getDate())
                .state(tasks.getState())
                .daysLate((LocalDateTime.now().getDayOfYear() - tasks.getDate().getDayOfYear()) + 1)
                .employeeEntity(tasks.getEmployeeEntity())
                .build();
    }

    public static Tasks convertToModel(TaskEntity taskEntity) {
        return Tasks.builder()
                .id(taskEntity.getId())
                .title(taskEntity.getTitle())
                .date(taskEntity.getDate())
                .state(taskEntity.getState())
                .daysLate((LocalDateTime.now().getDayOfYear() - taskEntity.getDate().getDayOfYear()) + 1)
                .employeeEntity(taskEntity.getEmployeeEntity())
                .build();
    }
}
