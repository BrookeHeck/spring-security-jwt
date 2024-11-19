package com.games.flashcard.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto implements Serializable {
    private long id;
    private String displayName;
    private String organizationCode;
    private LocalDateTime dateCreated;
}
