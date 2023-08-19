package com.task.agrl.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "TASK")
public class Task implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TASK",nullable = false)
    private Integer idTask;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "STATUS",nullable = false)
    private String status;

}
