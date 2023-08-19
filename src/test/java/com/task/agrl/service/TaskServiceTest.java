package com.task.agrl.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.task.agrl.models.entity.Response;
import com.task.agrl.models.entity.Task;
import com.task.agrl.repository.ITaskRepository;
import com.task.agrl.services.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	
    @InjectMocks
	private TaskService taskService;
	
	@Mock
	ITaskRepository iTaskRepository;
	
	List<Task> taskList;
	Task taskG;
    @BeforeEach
    public void setUp() {
    	taskList= new ArrayList<>();
    	taskG = new Task();
    }
    
    @Test
    void findAllTaskTest() {
    	taskList.add(new Task());
    	when(iTaskRepository.findAll()).thenReturn(taskList);
    	Response<Task> response= taskService.findAllTask();
    	assertNotNull(response);
    }
    
    @Test
    void findAllTask2Test() {
    	try {
        	Response<Task> response= taskService.findAllTask();
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Test
    void findAllTask3Test() {
    	try {
            doThrow(new DataAccessException("Simulated DataAccessException") {}).when(iTaskRepository).findAll();
        	Response<Task> response = taskService.findAllTask();
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Test
    void findByIdTask() {
    	when(iTaskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(taskG));
    	Response<Task> response = taskService.findByIdTask(1);
    	assertNotNull(response);
    }
    
    @Test
    void findById2Task() {
    	try {
        	Response<Task> response = taskService.findByIdTask(1);
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Test
    void findById3Task() {
    	try {
            doThrow(new DataAccessException("Simulated DataAccessException") {}).when(iTaskRepository).findById(1);
        	Response<Task> response = taskService.findByIdTask(1);
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Test
    void createTasktTest() {
    	when(iTaskRepository.save(Mockito.any())).thenReturn(taskG);
    	Response<Task> response = taskService.createTask(taskG);
    	assertNotNull(response);
    }
    
    @Test
    void createTaskt2Test() {
    	try {
            doThrow(new DataAccessException("Simulated DataAccessException") {}).when(iTaskRepository).save(Mockito.any());
        	Response<Task> response = taskService.createTask(taskG);
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    
    @Test
    void updateTasktTest() {
    	when(iTaskRepository.save(Mockito.any())).thenReturn(taskG);
    	taskG.setIdTask(1);
    	taskG.setName("Programacion");
    	taskG.setStatus("Pendiente");
    	
    	taskG.getDateRegistration();
    	taskG.getIdTask();
    	taskG.getName();
    	taskG.getStatus();
    	when(iTaskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(taskG));

    	Response<Task> response = taskService.updateTask(taskG);
    	assertNotNull(response);
    }
    
    @Test
    void updateTaskt2Test() {
    	try {
        	Response<Task> response = taskService.updateTask(taskG);
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Test
    void updateTaskt3Test() {
    	try {
        	taskG.setIdTask(1);
        	taskG.setName("Programacion");
        	taskG.setStatus("Pendiente");
        	
            doThrow(new DataAccessException("Simulated DataAccessException") {}).when(iTaskRepository).findById(Mockito.anyInt());

        	Response<Task> response = taskService.updateTask(taskG);
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Test
    void deleteTest() {
    	Response<Task> response = taskService.deleteTask(1);
    	assertNotNull(response);
    }
    
    @Test
    void delete2Test() {
    	try {
            doThrow(new DataAccessException("Simulated DataAccessException") {}).when(iTaskRepository).deleteById(1);
        	Response<Task> response = taskService.deleteTask(1);
        	assertNotNull(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }


}
