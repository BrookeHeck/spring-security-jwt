package com.games.flashcard.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardSetDto {
    private long id;
    private long organizationId;
    Set<Long> flashcardIds;
}
