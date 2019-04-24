package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloValidator {

    private static final Logger LOGGER= LoggerFactory.logger(TrelloValidator.class);

    public void validateCard(final TrelloCard trelloCard)
    {
        if(trelloCard.getName().contains("test"))
        {
            LOGGER.info("Someone is testing my application");
        } else{
            LOGGER.info("Seems that my application is used in proper way.");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards)
    {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards=trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards have been filtered. Current list size: "+filteredBoards.size());
        return filteredBoards;
    }
}
