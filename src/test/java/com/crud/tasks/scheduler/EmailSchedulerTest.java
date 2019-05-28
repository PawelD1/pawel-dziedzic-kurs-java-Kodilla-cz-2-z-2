package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.crud.tasks.domain.Mail;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void sendInformationEmailTest() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");
        when(taskRepository.count()).thenReturn(5L);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService).send(isNotNull(Mail.class));
    }
}