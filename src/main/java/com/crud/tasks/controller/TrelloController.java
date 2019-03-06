package com.crud.tasks.controller;

import com.crud.tasks.domain.CreateTelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoardsPublic();

        trelloBoards.stream()
                .filter(t -> t.getId() != null && t.getName() != null && t.getName().contains("Kodilla"))

                .forEach(trelloBoardDto -> {
                    System.out.println(trelloBoardDto.getName()+"-"+trelloBoardDto.getId());
                    trelloBoardDto.getLists().forEach(trelloList -> System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));


                });
    }
    @RequestMapping(method=RequestMethod.POST, value="createTrelloCard")
    public CreateTelloCard createTelloCard(@RequestBody TrelloCardDto trelloCardDto)
    {
        return trelloClient.createNewCard(trelloCardDto);
    }
}



























