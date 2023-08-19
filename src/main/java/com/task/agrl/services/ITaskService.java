package com.task.agrl.services;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;

public interface ITaskService {

    public Response<Task> findAllTask();
    public Response<Task> findByIdTask(Integer idTask);
    public Response<Task> createTask(Task task);
    public Response<Task> updateTask(Task task);
    public Response<Task> deleteTask(Integer idTask);

}
