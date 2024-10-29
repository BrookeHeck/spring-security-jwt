package com.games.flashcard.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "organizationId")
    private Organization organizationId;
    @OneToOne
    @JoinColumn(name = "moderator")
    private AppUser moderator;
    @OneToMany
    private Set<FlashcardSet> flashcardSets;
    private int teamOnePoints;
    private int teamTwoPoints;



}
