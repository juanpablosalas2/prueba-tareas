package com.example.pruebatareas.domain.usecase.interfaces;

import com.example.pruebatareas.domain.model.Tasks;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskUseCase {

    Mono<Tasks> createTask(Tasks tasks);

    Mono<Void> deleteTask(Long idTask);

    Mono<Tasks> updateTask(Tasks tasks);

    Flux<Tasks> getAllTasks();
}
