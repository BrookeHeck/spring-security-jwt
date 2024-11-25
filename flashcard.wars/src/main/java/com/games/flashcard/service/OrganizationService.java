package com.games.flashcard.service;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.model.query_models.OrganizationOverviewDetails;

import java.util.List;

public interface OrganizationService {
    OrganizationDto findOrganizationById(long orgId);

    List<OrganizationDto> findAllOrganizations();

    OrganizationDto findOrganizationByOrganizationCode(String code);

    boolean updateOrganizationDisplayName(String displayName, long orgId);

    OrganizationDto createOrganization(OrganizationDto organizationDto);

    void deleteOrganizationById(long orgId);

    List<OrganizationOverviewDetails> getOrganizationOverviewDetails();
}
