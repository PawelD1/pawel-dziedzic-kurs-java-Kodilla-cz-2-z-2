package com.crud.tasks.config;

import com.crud.tasks.repository.TaskRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Autowired
    TaskRepository taskRepository;
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${company.name}")
    private  String companyName;

    @Value("${company.address}")
    private String companyAddress;

    @Value("${company.email}")
    private String companyEmail;
}