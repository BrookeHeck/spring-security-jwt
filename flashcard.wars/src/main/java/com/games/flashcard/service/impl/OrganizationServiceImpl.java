package com.games.flashcard.service.impl;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.model.entities.Organization;
import com.games.flashcard.repository.OrganizationRepository;
import com.games.flashcard.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository orgRepo;
    private final ModelMapper modelMapper;
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

    @Override
    public Organization createOrganization(OrganizationDto organizationDto) {
        Organization organization = modelMapper.map(organizationDto, Organization.class);
        return orgRepo.save(organization);
    }

    @Override
    public void deleteOrganizationById(long orgId) {
        orgRepo.deleteById(orgId);
    }


}
