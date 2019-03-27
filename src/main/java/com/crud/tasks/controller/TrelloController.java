package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    //private TrelloClient trelloClient;
    private TrelloService trelloService;

//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
//    public void getTrelloBoards() {
//
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoardsPublic();
//
//        trelloBoards.stream()
//                .filter(t -> t.getId() != null && t.getName() != null && t.getName().contains("Kodilla"))
//
//                .forEach(trelloBoardDto -> {
//                    System.out.println(trelloBoardDto.getName()+"-"+trelloBoardDto.getId());
//                    trelloBoardDto.getLists().forEach(trelloList -> System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//
//
//                });
//    }
//    @RequestMapping(method=RequestMethod.GET, value="getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards()
//    {
//        return trelloClient.getTrelloBoardsPublic();
//    }
//    @RequestMapping(method=RequestMethod.POST, value="createTrelloCard")
//    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto)
//    {
//        return trelloClient.createNewCard(trelloCardDto);
//    }
        @RequestMapping(method=RequestMethod.GET, value="/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards()
    {
        return trelloService.fetchTrelloBoards();
    }
    @RequestMapping(method=RequestMethod.POST, value="/createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto)
    {
        return trelloService.createdTrelloCard(trelloCardDto);
    }

}



























