package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import com.crud.tasks.controller.TaskNotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication  {

    public static void main(String[] args) throws TaskNotFoundException { //spring actuator
        SpringApplication.run(TasksApplication.class, args);
        //ctrl+alt+L
        //l.ctrl i LPM
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
//    {
//        return application.sources(TasksApplication.class);
//    }
}

