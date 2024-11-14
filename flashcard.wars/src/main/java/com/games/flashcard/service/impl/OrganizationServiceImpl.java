package com.games.flashcard.service.impl;

import com.games.flashcard.model.entities.Organization;
import com.games.flashcard.repository.OrganizationRepository;
import com.games.flashcard.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository orgRepo;
    @Override
    public Organization findOrganizationById(long orgId) {
        return orgRepo.findOrganizationById(orgId);
    }

    @Override
    public List<Organization> findAllOrganizations() {
        return orgRepo.findAllOrganizations();
    }

    @Override
    public Organization findOrganizationByOrganizationCode(String code) {
        return orgRepo.findOrganizationByOrganizationCode(code);
    }

    @Override
    public boolean updateOrganizationDisplayName(String displayName, long orgId) {
        return orgRepo.updateOrganizationDisplayNameById(displayName, orgId) == 1;
    }
}
