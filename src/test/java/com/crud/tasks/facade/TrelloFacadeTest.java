package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional

public class TrelloFacadeTest {

    @Autowired
    private TrelloFacade trelloFacade;

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloValidator trelloValidator;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    TaskRepository taskRepository;

    @Test

    public void shouldFetchEmptyList() {

        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {

        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("my_task", trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("my_list", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldFetchEmptyCard() {

        //Given
        TrelloCard trelloCard = new TrelloCard("card", "cardOfTrello", "pos1", "listId1");
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "cardOfTrello", "pos1", "listId1");

        //When
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto());
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(null);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(null);

        //Then
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);
        assertEquals(null, createdTrelloCardDto);
    }
}

