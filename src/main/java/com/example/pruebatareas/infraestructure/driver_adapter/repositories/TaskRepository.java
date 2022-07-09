package com.example.pruebatareas.infraestructure.driver_adapter.repositories;

import com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
}
