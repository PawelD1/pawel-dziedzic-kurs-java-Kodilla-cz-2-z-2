package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private MimeMessage toCc;

    public void setCc() throws MessagingException {
        toCc.addRecipient(Message.RecipientType.CC, new InternetAddress(mailTo));
        if (toCc == null)
            mailTo = null;

    }
}
//https://www.tutorialspoint.com/java/java_sending_email.htm