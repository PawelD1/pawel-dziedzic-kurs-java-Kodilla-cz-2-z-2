package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TrelloBoard {
    String id;
    String name;
    private List<TrelloList> lists;
}
