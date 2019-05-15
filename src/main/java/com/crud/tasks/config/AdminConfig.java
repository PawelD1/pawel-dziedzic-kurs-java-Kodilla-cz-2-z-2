package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
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

    public LocalDate sendingEmailDaily(ArrayList<LocalDate> localDates)
    {
        for(int i=0;i<localDates.size();i++) {
            if (localDates.get(i).getDayOfYear() == LocalDate.now().getDayOfYear())
                return LocalDate.now();
        }
        return null;

    }
}
