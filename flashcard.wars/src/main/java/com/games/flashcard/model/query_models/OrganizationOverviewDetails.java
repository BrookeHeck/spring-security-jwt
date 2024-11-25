package com.games.flashcard.model.query_models;

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
public class OrganizationOverviewDetails implements Serializable {
    private long organizationId;
    private String organizationDisplayName;
    private int numberOfAdmins;
    private int numberOfTeachers;
    private int numberOfAssistants;
    private int numberOfStudents;
    private LocalDateTime organizationCreationDate;
}
