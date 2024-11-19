package com.games.flashcard.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto implements Serializable {
    private long id;
    LocalDateTime dateStarted;
    private String gameIdentifier;
    private long organization;
    private UserDto teamOneCaptain;
    private UserDto teamTwoCaptain;
    private Set<Long> flashcardSets;
    private int teamOnePoints;
    private int teamTwoPoints;
    private boolean completed;
}
