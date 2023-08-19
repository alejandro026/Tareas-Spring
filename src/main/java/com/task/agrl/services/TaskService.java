package com.task.agrl.services;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;
import com.task.agrl.repository.ITaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Response<Task> response = new Response<>();
        List<Task> listTask= new ArrayList<>();


        try {
            listTask= taskRepository.findAll();
            response.setList(listTask);
            response.setCount(listTask.size());
            response.setMessage("Tareas encontradas con éxito");
        }catch (DataAccessException e){
            log.error(e.getMessage());
           // throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al consultar las publicaciones.");

        }

        return response;
    }

    /**
     * @param idTask
     * @return
     */
    @Override
    public Response<Task> findByIdTask(Integer idTask) {
        return null;
    }

    /**
     * @param task
     * @return Response<Task>
     */
    @Override
    public Response<Task> createTask(Task task) {
        return null;
    }

    /**
     * @param task
     * @return Response<Task>
     */
    @Override
    public Response<Task> updatePost(Task task) {
        return null;
    }

    /**
     * @param idTask
     * @return Response<Task>
     */
    @Override
    public Response<Task> deletePost(Integer idTask) {
        return null;
    }
}
