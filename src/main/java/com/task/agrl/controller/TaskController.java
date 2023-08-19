package com.task.agrl.controller;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;
import com.task.agrl.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ITaskService iTaskService;

    /**
     * Maneja la solicitud para obtener todas las tareas.
     *
     * @return ResponseEntity con una lista de tareas en el cuerpo de la respuesta.
     */
    @GetMapping(path = "/findAllTask", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Task>> findAllTask(){
        // Invoca al servicio para obtener todas las tareas
        Response<Task> response = iTaskService.findAllTask();
        // Retorna una respuesta con la lista de tareas y el estado HTTP OK (200)
        return ResponseEntity.ok(response);
    }


    /**
     * Maneja la solicitud para obtener la tarea por su id.
     *
     * @return ResponseEntity con una tarea en el cuerpo de la respuesta.
     */
    @GetMapping(path = "/findByIdTask/{idTask}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Task>> findByIdTask(@PathVariable("idTask") Integer idTask){
        // Invoca al servicio para obtener una tarea
        Response<Task> response = iTaskService.findByIdTask(idTask);

        // Retorna una respuesta con la tarea encontrada y el estado HTTP OK (200)
        return ResponseEntity.ok(response);
    }

    /**
     * Maneja la solicitud para crear una nueva tarea.
     *
     * @param task los datos de la tarea a crear.
     * @return ResponseEntity con la tarea creado en el cuerpo de la respuesta.
     */
    @PostMapping(path = "/createTask", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Task>> createTask(@RequestBody Task task){
        // Invoca al servicio para crear la tarea utilizando los datos proporcionados
        Response<Task> response = iTaskService.createTask(task);
        // Retorna una respuesta con la tarea creada y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Maneja la solicitud para actualizar una tarea existente.
     *.
     * @param task los datos de la tarea actualizada.
     * @return ResponseEntity con la tarea actualizada en el cuerpo de la respuesta.
     */
    @PutMapping(path = "/updateTask", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Task>> updateTask(@RequestBody Task task) {
        // Invoca al servicio para actualizar la tarea utilizando los datos proporcionados
        Response<Task> response = iTaskService.updateTask(task);

        // Retorna una respuesta con la tarea actualizada y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Maneja la solicitud para eliminar una tarea existente por su ID.
     *
     * @param idTask el ID de la tarea a eliminar.
     * @return ResponseEntity con información sobre el resultado de la eliminación de la tarea de la respuesta.
     */
    @DeleteMapping(path = "/deleteTask/{idTask}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Task>> deleteTask(@PathVariable("idTask") Integer idTask) {
        // Invoca al servicio para eliminar la tarea por su ID
        Response<Task> response = iTaskService.deleteTask(idTask);

        // Retorna una respuesta con información sobre el resultado de la eliminación y el estado HTTP OK (200)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
