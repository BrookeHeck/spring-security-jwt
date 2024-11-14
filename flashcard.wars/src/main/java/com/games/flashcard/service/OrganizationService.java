package com.games.flashcard.service;

import com.games.flashcard.model.entities.Organization;

import java.util.List;

public interface OrganizationService {
    Organization findOrganizationById(long orgId);

    List<Organization> findAllOrganizations();

    Organization findOrganizationByOrganizationCode(String code);

    boolean updateOrganizationDisplayName(String displayName);
}
