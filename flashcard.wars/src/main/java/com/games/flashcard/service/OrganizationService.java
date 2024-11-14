package com.games.flashcard.service;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.model.entities.Organization;

import java.util.List;

public interface OrganizationService {
    Organization findOrganizationById(long orgId);

    List<Organization> findAllOrganizations();

    Organization findOrganizationByOrganizationCode(String code);

    boolean updateOrganizationDisplayName(String displayName, long orgId);

    Organization createOrganization(OrganizationDto organizationDto);

    void deleteOrganizationById(long orgId);
}
