package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
i
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Autowired
    private RestTemplate restTemplate;
    public List<TrelloBoardDto> getTrelloBorads()
    {
        TrelloBoardDto[] boardsResponse=restTemplate.getForObject(trelloApiEndpoint+"/members/pawedziedzic2/boards"+"?key="+trelloAppKey+"&token="+trelloToken, TrelloBoardDto[].class);
    }
}
