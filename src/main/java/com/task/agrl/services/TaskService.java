package com.task.agrl.services;

import com.task.agrl.exceptions.BusinessException;
import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;
import com.task.agrl.repository.ITaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService implements ITaskService{

    @Autowired
    private ITaskRepository taskRepository;

    /**
     * @return Response<Task>
     */
    @Override
    public Response<Task> findAllTask() {
        // Se crea una instancia de la clase Response que se retornará.
        Response<Task> response = new Response<>();

        List<Task> listTask;
        try {
            listTask= taskRepository.findAll();
            if (listTask.size()==0){
                throw new BusinessException(HttpStatus.NOT_FOUND, "No existen tareas en la base de datos.");
            }
            response.setList(listTask);
            response.setCount(listTask.size());
            response.setMessage("Tareas encontradas con éxito");
        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al consultar las tareas.");
        }
        return response;
    }

    /**
     * @param idTask integer
     * @return Response<Task>
     */
    @Override
    public Response<Task> findByIdTask(Integer idTask) {
        // Se crea una instancia de la clase Response que se retornará.
        Response<Task> response = new Response<>();
        Task task;
        try {
            Optional<Task> optionalTask= taskRepository.findById(idTask);
            if(optionalTask.isPresent()){
                task= optionalTask.get();
                response.setData(task);
                response.setStatus("OK");
                response.setMessage("Tarea obtenida con correctamente");
            }else{
                throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontró la tarea");
            }
        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al consultar la tarea.");
        }
        return response;
    }

    /**
     * @param task
     * @return Response<Task>
     */
    @Override
    public Response<Task> createTask(Task task) {
        // Se crea una instancia de la clase Response que se retornará.
        Response<Task> response = new Response<>();
        try {
            Task taskSave= taskRepository.save(task);
            response.setMessage("Tarea guardada con exitó");
            response.setData(taskSave);
            response.setStatus("OK");
        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al guardar la tarea.");
        }
        return response;
    }

    /**
     * @param task
     * @return Response<Task>
     */
    @Override
    public Response<Task> updateTask(Task task) {
        // Se crea una instancia de la clase Response que se retornará.
        Response<Task> response = new Response<>();
        Task currentTask;
        Task updatedTask = null;
        try {
           Optional <Task> optionalTask = taskRepository.findById(task.getIdTask());
           if (optionalTask.isEmpty()){
               throw new BusinessException(HttpStatus.NOT_FOUND, "Tarea no existe para actualizar");
           }
           currentTask= optionalTask.get();
           if (task.getName()!=null && !task.getName().equals("")){
               currentTask.setName(task.getName());
           }
           if(task.getStatus()!=null && !task.getStatus().equals("")){
               currentTask.setStatus(task.getStatus());
           }
            updatedTask= taskRepository.save(currentTask);
            response.setMessage("Tarea actualizada con exitó");
            response.setData(updatedTask);
            response.setStatus("OK");

        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al actualizar la tarea");
        }
        return response;
    }

    /**
     * @param idTask
     * @return Response<Task>
     */
    @Override
    public Response<Task> deleteTask(Integer idTask) {
        // Se crea una instancia de la clase Response que se retornará.
        Response<Task> response = new Response<>();

        try {
            taskRepository.deleteById(idTask);
            response.setMessage("Tarea eliminada con exitó");
            response.setStatus("OK");
        }catch (DataAccessException e){
            log.error(e.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al eliminar la tarea.");
        }
        return response;
    }
}
