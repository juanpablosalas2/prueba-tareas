package com.example.pruebatareas.domain.usecase;

import com.example.pruebatareas.domain.model.Tasks;
import com.example.pruebatareas.domain.model.exceptions.TaskException;
import com.example.pruebatareas.domain.usecase.interfaces.TaskUseCase;
import com.example.pruebatareas.infraestructure.driver_adapter.repositories.TaskRepository;
import com.example.pruebatareas.infraestructure.driver_adapter.repositories.entities.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskUseCaseImpl implements TaskUseCase {

    private static final String ID_NOT_FOUND = "task With ID %d not found";
    private final TaskRepository taskRepository;

    @Override
    public Mono<Tasks> createTask(Tasks tasks) {
        return Mono.just(tasks)
                .map(TaskEntity::convertToEntity)
                .map(taskRepository::save)
                .map(TaskEntity::convertToModel)
                .onErrorResume(throwable -> Mono.error(new TaskException("Task could not be saved" + throwable.getMessage(), throwable)));
    }

    @Override
    public Mono<Void> deleteTask(Long idTask) {
        return Mono.just(idTask)
                .map(taskRepository::existsById)
                .switchIfEmpty(Mono.error(new TaskException(String.format(ID_NOT_FOUND, idTask))))
                .map(exist -> Boolean.TRUE.equals(exist) ? idTask : null)
                .flatMap(aLong -> {
                    taskRepository.deleteById(aLong);
                    return Mono.when();
                })
                .onErrorResume(throwable -> Mono.error(new TaskException("Task could not be deleted", throwable)));
    }

    @Override
    public Mono<Tasks> updateTask(Tasks tasks) {
        return Mono.just(tasks)
                .map(TaskEntity::convertToEntity)
                .map(task1 -> taskRepository.findById(task1.getId()))
                .switchIfEmpty(Mono.error(new TaskException(String.format(ID_NOT_FOUND, tasks.getId()))))
                .map(taskEntity -> {
                    TaskEntity newTask = TaskEntity.builder()
                            .id(taskEntity.get().getId()).title(tasks.getTitle())
                            .state(tasks.getState())
                            .date(tasks.getDate())
                            .daysLate(taskEntity.get().getDaysLate())
                            .employeeEntity(tasks.getEmployeeEntity())
                            .build();

                    return taskRepository.save(newTask);
                })
                .map(TaskEntity::convertToModel)
                .onErrorResume(throwable -> Mono.error(new TaskException("Task Could not be Update ")));
    }



    @Override
    public Flux<Tasks> getAllTasks() {
        return Flux.fromIterable(taskRepository.findAll())
                .map(taskEntity -> TaskEntity.convertToModel(taskEntity))
                .onErrorResume(throwable -> Mono.error(new TaskException("Task could not be founded",throwable)));
    }
}
