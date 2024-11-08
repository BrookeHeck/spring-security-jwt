package com.games.flashcard.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardDto implements Serializable {
    private long id;
    private String question;
    private String answer;
    private int points;
    private long flashcardSet;
}
