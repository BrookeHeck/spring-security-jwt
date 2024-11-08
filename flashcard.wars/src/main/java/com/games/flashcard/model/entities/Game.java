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
    @ManyToMany
    @JoinTable(
            name = "game_flashcard_set",
            joinColumns = @JoinColumn(name = "flashcard_set_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<FlashcardSet> flashcardSets;
    private int teamOnePoints;
    private int teamTwoPoints;



}
