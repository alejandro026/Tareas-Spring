package com.task.agrl.services;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;

public interface ITaskService {

    public Response<Task> findAllTask();
    public Response<Task> findByIdTask(Integer idTask);
    public Response<Task> createTask(Task task);
    public Response<Task> updatePost(Task task);
    public Response<Task> deletePost(Integer idTask);

}
