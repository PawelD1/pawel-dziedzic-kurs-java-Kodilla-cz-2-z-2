package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Autowired
    private MailCreatorService mailCreatorService;
    private static final Logger LOGGER = LoggerFactory.logger(SimpleMailMessage.class);

    public JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation...");
        try {
            //SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);

        }

    }

    private MimeMessagePreparator createMimeMessage(final Mail mail)
    {
        return mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }



    private SimpleMailMessage createMailMessage(final Mail mail)  {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc()!=null) {
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }



}
