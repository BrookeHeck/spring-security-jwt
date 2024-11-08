package com.games.flashcard.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrganizationDto implements Serializable {
    private long id;
    private String displayName;
    private String organizationCode;
    private LocalDateTime dateCreated;
}
