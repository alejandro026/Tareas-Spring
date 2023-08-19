package com.task.agrl.repository;

import com.task.agrl.models.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Integer> {

}
