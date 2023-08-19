package com.task.agrl.controller;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;
import com.task.agrl.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ITaskService iTaskService;

    /**
     * Maneja la solicitud para obtener todos los componentes existentes.
     *
     * @return ResponseEntity con una lista de comentarios en el cuerpo de la respuesta.
     */
    @GetMapping(path = "/findAllTask", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Task>> findAllPost(){
        // Invoca al servicio para obtener todos los comentarios
        Response<Task> response = iTaskService.findAllTask();

        // Retorna una respuesta con la lista de comentarios y el estado HTTP OK (200)
        return ResponseEntity.ok(response);
    }

}
