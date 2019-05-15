package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

//    public String buildTrelloCardEmail(String message)
//    {
//        Context context=new Context();
//        context.setVariable("message", message);
//        return templateEngine.process("mail/created-trello-card-mail",context);
//    }

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:63342/tasks");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_address", adminConfig.getCompanyName());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("amount_tasks", taskRepository.count());
        context.setVariable("is_email_sended", true);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendingEmailDaily() {
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                "Automatic message",
                "Currently in database you got: " + size + "tasks",
                "")
        );
    }
}