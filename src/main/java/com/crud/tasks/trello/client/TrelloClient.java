package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreateTelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloUsername;
    @Autowired
    private RestTemplate restTemplate;

    private List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/pawedziedzic2/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();


        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        int length = boardsResponse.length;

        for (int i = 0; i < length; i++) {
            if (Optional.ofNullable(boardsResponse[i]).isPresent()) {
                final Optional<TrelloBoardDto> board = Optional.of(boardsResponse[i]);
                return Arrays.asList(boardsResponse);
            }


        }
        return new ArrayList<>();

    }

    public List<TrelloBoardDto> getTrelloBoardsPublic() {
        return getTrelloBoards();
    }
    public CreateTelloCard createNewCard(TrelloCardDto trelloCardDto)
    {
        URI url= UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint+"/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("post",trelloCardDto.getPost())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();
        return restTemplate.postForObject(url, null, CreateTelloCard.class);
    }

}
