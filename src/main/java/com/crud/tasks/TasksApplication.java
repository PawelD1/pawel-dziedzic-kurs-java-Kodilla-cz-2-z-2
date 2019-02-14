package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import com.crud.tasks.controller.TaskNotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

    public static void main(String[] args) throws TaskNotFoundException {
        SpringApplication.run(TasksApplication.class, args);
        //ctrl+alt+L
        //l.ctrl i LPM
    }
}

