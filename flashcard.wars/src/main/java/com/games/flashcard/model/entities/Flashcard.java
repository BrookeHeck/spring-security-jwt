package com.games.flashcard.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String question;
    private String answer;
    private int points;
    @ManyToOne
    FlashcardSet flashcardSet;

}
