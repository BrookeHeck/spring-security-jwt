package com.games.flashcard.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

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
