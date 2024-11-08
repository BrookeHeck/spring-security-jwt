package com.games.flashcard.model.dtos;

import java.time.LocalDateTime;
import java.util.Set;

public class GameDto {
    private long id;
    LocalDateTime dateStarted;
    private String gameIdentifier;
    private long organization;
    private UserDto teamOneCaptain;
    private UserDto teamTwoCaptain;
    private Set<Long> flashcardSetIds;
    private int teamOnePoints;
    private int teamTwoPoints;
    private boolean completed;
}
