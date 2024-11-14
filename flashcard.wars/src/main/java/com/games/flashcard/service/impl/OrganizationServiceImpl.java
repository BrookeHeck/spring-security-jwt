package com.games.flashcard.service.impl;

import com.games.flashcard.model.entities.Organization;
import com.games.flashcard.service.OrganizationService;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public Organization findOrganizationById(long orgId) {
        return null;
    }

    @Override
    public List<Organization> findAllOrganizations() {
        return null;
    }

    @Override
    public Organization findOrganizationByOrganizationCode(String code) {
        return null;
    }

    @Override
    public boolean updateOrganizationDisplayName(String displayName) {
        return false;
    }
}
