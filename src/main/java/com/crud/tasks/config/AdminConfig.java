package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Getter
public class AdminConfig {
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

   @Scheduled(cron="0 0 12 * * *")
    public LocalDate sendingEmailDaily(ArrayList<LocalDate> localDates)
    {
        return LocalDate.now();
    }
}