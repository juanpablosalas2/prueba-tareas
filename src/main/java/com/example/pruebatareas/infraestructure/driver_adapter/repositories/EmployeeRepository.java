package com.example.pruebatareas.infraestructure.driver_adapter.repositories;

import com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
