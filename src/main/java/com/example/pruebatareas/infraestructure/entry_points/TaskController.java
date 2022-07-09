package com.example.pruebatareas.infraestructure.entry_points;

import com.example.pruebatareas.domain.model.GeneralResponse;
import com.example.pruebatareas.domain.model.Tasks;
import com.example.pruebatareas.domain.usecase.interfaces.TaskUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private static final String DELETED_SUCCESS = "task deleted successfully";
    private static final String UPDATED_SUCCESS = "task updated successfully";
    private static final String ADDED_SUCCESS = "task added successfully";

    private static final String TASK = "task";
    private final GeneralResponse<Tasks> generalResponse;
    private final GeneralResponse<Boolean> responseDelete;
    private final TaskUseCase taskUseCase;

    @PostMapping("/create")
    public Mono<ResponseEntity<GeneralResponse<Tasks>>> createTask(@Valid @RequestBody Tasks task){
        return taskUseCase.createTask(task)
                .map(album1 -> {
                    HashMap<String, Tasks> data = new HashMap<>();
                    data.put(TASK, album1);
                    return generalResponse.generateGeneralResponseSuccess(Boolean.TRUE, ADDED_SUCCESS, data, HttpStatus.CREATED);
                })
                .onErrorResume(throwable -> Mono.just(generalResponse.generateGeneralResponseError(Boolean.FALSE, throwable.getMessage(), HttpStatus.BAD_REQUEST)));
    }
    @PutMapping("/update")
    public Mono<ResponseEntity<GeneralResponse<Tasks>>> updateTask(@Valid @RequestBody Tasks task){
        return taskUseCase.updateTask(task)
                .map(album1 -> {
                    HashMap<String, Tasks> data = new HashMap<>();
                    data.put(TASK, album1);
                    return generalResponse.generateGeneralResponseSuccess(Boolean.TRUE, UPDATED_SUCCESS, data, HttpStatus.ACCEPTED);
                })
                .onErrorResume(throwable -> Mono.just(generalResponse.generateGeneralResponseError(Boolean.FALSE, throwable.getMessage(), HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/delete/{idTask}")
    public Mono<ResponseEntity<GeneralResponse<Boolean>>> deleteTask(@PathVariable Long idTask){
        return taskUseCase.deleteTask(idTask)
                .map(photo1 -> {
                    HashMap<String, Boolean> data = new HashMap<>();
                    data.put(TASK, Boolean.TRUE);
                    return responseDelete.generateGeneralResponseSuccess(Boolean.TRUE, DELETED_SUCCESS, data, HttpStatus.ACCEPTED);
                })
                .onErrorResume(throwable -> Mono.just(responseDelete.generateGeneralResponseError(Boolean.FALSE, throwable.getMessage(), HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public Flux<Tasks> getAllTasks() {
        return taskUseCase.getAllTasks();
    }

}
