package com.games.flashcard.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreationTimestamp
    LocalDateTime dateStarted;
    private String gameIdentifier;
    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @OneToOne
    private AppUser teamOneCaptain;
    @OneToOne
    private AppUser teamTwoCaptain;
    @ManyToMany
    @JoinTable(
            name = "game_flashcard_set",
            joinColumns = @JoinColumn(name = "flashcard_set_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<FlashcardSet> flashcardSets;
    private int teamOnePoints = 0;
    private int teamTwoPoints = 0;
    private boolean completed = false;



}
