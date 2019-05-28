package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoardNamedTest = new TrelloBoard("0", "test", new ArrayList <>());
        TrelloBoard trelloBoardNamedName = new TrelloBoard("1", "name", new ArrayList <>());
        List <TrelloBoard> trelloBoardsList = new ArrayList <>();
        trelloBoardsList.add(trelloBoardNamedTest);
        trelloBoardsList.add(trelloBoardNamedName);

        //When
        List <TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoardsList);
        List <TrelloBoard> validatedEmptyTrelloBoards = trelloValidator.validateTrelloBoards(new ArrayList <>());

        //Then
        assertEquals(1, validatedTrelloBoards.size());
        assertEquals("name", validatedTrelloBoards.get(0).getName());
        assertEquals(0, validatedEmptyTrelloBoards.size());
    }
}
