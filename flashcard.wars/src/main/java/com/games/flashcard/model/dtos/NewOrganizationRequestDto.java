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
public class NewOrganizationRequestDto implements Serializable {
    private long id;
    private OrganizationDto organizationDto;
    private UserDto userDto;
    private LocalDateTime timeOfInsert;
}
