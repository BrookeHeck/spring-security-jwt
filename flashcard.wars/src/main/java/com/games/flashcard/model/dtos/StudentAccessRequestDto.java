package com.games.flashcard.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAccessRequestDto implements Serializable {
    private long id;
    private OrganizationDto organization;
    private UserDto appUser;
    private LocalDateTime timeOfInsert;
}
