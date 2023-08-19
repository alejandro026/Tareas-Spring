package com.task.agrl.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_REGISTRATION", nullable = false)
    private Date dateRegistration;

    @PrePersist
    private void onCreate() {
        dateRegistration = new Date();
    }

}
