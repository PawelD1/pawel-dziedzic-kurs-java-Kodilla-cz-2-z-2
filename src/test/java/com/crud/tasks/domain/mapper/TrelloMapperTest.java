package com.crud.tasks.domain.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "one", true);
        TrelloList trelloList2 = new TrelloList("2", "two", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);
        lists.add(trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "board", lists);
        List<TrelloBoard> listsBoard = new ArrayList<>();
        listsBoard.add(trelloBoard);
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        List<TrelloBoardDto> listsBoardDto = trelloMapper.mapToBoardsDto(listsBoard);

        //Then
        Assert.assertEquals(trelloList.getId(), listsBoardDto.get(0).getLists().get(0).getId());
        Assert.assertEquals(trelloBoard.getId(), listsBoardDto.get(0).getId());
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloList = new TrelloListDto("1", "one", true);
        TrelloListDto trelloList2 = new TrelloListDto("2", "two", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloList);
        lists.add(trelloList2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "board", lists);
        List<TrelloBoardDto> listsBoardDto = new ArrayList<>();
        listsBoardDto.add(trelloBoardDto);
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        List<TrelloBoard> listsBoard = trelloMapper.mapToBoards(listsBoardDto);

        //Then
        Assert.assertEquals(trelloList.getId(), listsBoard.get(0).getLists().get(0).getId());
        Assert.assertEquals(trelloBoardDto.getId(), listsBoard.get(0).getId());
    }

    @Test
    public void testMapToList()
    {
        //Given
        TrelloListDto trelloList = new TrelloListDto("1", "one", true);
        TrelloListDto trelloList2 = new TrelloListDto("2", "two", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloList);
        lists.add(trelloList2);
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        List<TrelloList> listsTrello = trelloMapper.mapToList(lists);

        //Then
        Assert.assertEquals(trelloList.getId(), listsTrello.get(0).getId());
    }

    @Test
    public void testMapToListDto()
    {
        //Given
        TrelloList trelloList = new TrelloList("1", "one", true);
        TrelloList trelloList2 = new TrelloList("2", "two", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);
        lists.add(trelloList2);
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        List<TrelloListDto> listsTrello = trelloMapper.mapToListDto(lists);

        //Then
        Assert.assertEquals(trelloList.getId(), listsTrello.get(0).getId());
    }

    @Test
    public void testMapToCardDto()
    {
        //Given
        TrelloCard trelloCard=new TrelloCard("card","trello card","pos1","listId1");
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        TrelloCardDto trelloCardDto= trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(),trelloCardDto.getName());

    }

    @Test
    public void testMapToCard()
    {
        //Given
        TrelloCardDto trelloCardDto=new TrelloCardDto("card","trello card","pos1","listId1");
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        TrelloCard trelloCard= trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getName(),trelloCard.getName());

    }
}

