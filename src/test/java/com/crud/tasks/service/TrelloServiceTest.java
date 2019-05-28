package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoardsEmptyListTest() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList <>());

        //When
        List <TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "shortUrl");
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");
        Mail mail = new Mail("mailTo", "subject", "message","");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto resultedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdTrelloCardDto.getId(), resultedTrelloCardDto.getId());
        assertEquals(createdTrelloCardDto.getName(), resultedTrelloCardDto.getName());
        assertEquals(createdTrelloCardDto.getShortUrl(), resultedTrelloCardDto.getShortUrl());
    }
}


