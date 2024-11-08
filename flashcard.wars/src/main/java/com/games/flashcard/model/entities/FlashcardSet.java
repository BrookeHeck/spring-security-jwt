package com.games.flashcard.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Organization organization;
    @OneToMany(mappedBy = "flashcardSet", fetch = FetchType.LAZY)
    Set<Flashcard> flashcards;
    @ManyToMany(mappedBy = "flashcardSets")
    Set<Game> games;
}
