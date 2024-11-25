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
public class OrganizationOverviewDetails implements Serializable {
    private long organizationId;
    private String organizationDisplayName;
    private LocalDateTime organizationCreationDate;
    private long numberOfAdmins;
    private long numberOfTeachers;
    private long numberOfAssistants;
    private long numberOfStudents;

    public OrganizationOverviewDetails(long organizationId, String organizationDisplayName, LocalDateTime organizationCreationDate,
                                       long numberOfAdmins, long numberOfTeachers, long numberOfAssistants, long numberOfStudents) {
        this.organizationId = organizationId;
        this.organizationDisplayName = organizationDisplayName;
        this.organizationCreationDate = organizationCreationDate;
        this.numberOfAdmins = numberOfAdmins;
        this.numberOfTeachers = numberOfTeachers;
        this.numberOfAssistants = numberOfAssistants;
        this.numberOfStudents = numberOfStudents;
    }
}
