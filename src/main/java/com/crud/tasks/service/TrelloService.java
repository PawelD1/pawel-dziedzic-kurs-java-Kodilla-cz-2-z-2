package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.smartcardio.Card;
import java.util.List;

import static sun.security.x509.X509CertInfo.SUBJECT;

@Service
public class TrelloService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService emailService;


    public List<TrelloBoardDto> fetchTrelloBoards()
    {
        return trelloClient.getTrelloBoardsPublic();
    }
    public CreatedTrelloCard createdTrelloCard(final TrelloCardDto trelloCardDto)
    {
        CreatedTrelloCard newCard=trelloClient.createNewCard(trelloCardDto);
        ofNullable(new Card).ifPresent(card->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "New card; "+card.getName()+" has been created on your Trello account",new MimeMessage()));
        return newCard;


    }
}
