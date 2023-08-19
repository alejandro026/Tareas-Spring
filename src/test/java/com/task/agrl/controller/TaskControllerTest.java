package com.task.agrl.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;
import com.task.agrl.services.ITaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    ITaskService iTaskService;
    
    Response<Task> resposeTask;

    @BeforeEach
    public void setUp() {
    	resposeTask= new Response<>();
    }

    @Test
    void findAllTaskTest(){
        ResponseEntity<Response<Task>> response= taskController.findAllTask();
        assertNotNull(response);
    }

    @Test
    void findByIdTaskTest(){
        ResponseEntity<Response<Task>> response= taskController.findByIdTask(1);
        assertNotNull(response);
    }

    @Test
    void createTaskTest(){
        ResponseEntity<Response<Task>> response= taskController.createTask(new Task());
        assertNotNull(response);
    }
    
    @Test
    void updateTaskTest() {
        ResponseEntity<Response<Task>> response= taskController.updateTask(new Task());
        assertNotNull(response);
    }
    
    @Test
    void deleteTaskTest() {
        ResponseEntity<Response<Task>> response= taskController.deleteTask(1);
        assertNotNull(response);
    }

}
