package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
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
    @Value("pawedziedzic2")
    private String trelloUsername;
    @Autowired
    private RestTemplate restTemplate;

    private List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/pawedziedzic2/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        ;
        int length = boardsResponse.length;
        for (int i = 0; i < length; i++) {
            final Optional<TrelloBoardDto> board = Optional.ofNullable(boardsResponse[i]);
            if (board.isPresent()) {
                return Arrays.asList(boardsResponse);
            }

        }
        return new ArrayList<>();
//        if (boardsResponse != null) {
//            return Arrays.asList(boardsResponse);
//        }
//        return new ArrayList<>();

        //TrelloBoardDto[] boardsResponse=restTemplate.getForObject(trelloApiEndpoint+"/members/pawedziedzic2/boards"+"?key="+trelloAppKey+"&token="+trelloToken, TrelloBoardDto[].class);
    }

    public List<TrelloBoardDto> getTrelloBoardsPublic() {
        return getTrelloBoards();
    }

}
