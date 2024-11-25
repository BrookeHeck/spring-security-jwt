package com.games.flashcard.service.impl;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.model.entities.Organization;
import com.games.flashcard.model.query_models.OrganizationOverviewDetails;
import com.games.flashcard.repository.OrganizationRepository;
import com.games.flashcard.service.OrganizationService;
import com.games.flashcard.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository orgRepo;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    @Override
    public OrganizationDto findOrganizationById(long orgId) {
        return modelMapper.map(orgRepo.findOrganizationById(orgId), OrganizationDto.class);
    }

    @Override
    public List<OrganizationDto> findAllOrganizations() {
        return orgRepo.findAllOrganizations().stream().map(org -> modelMapper.map(org, OrganizationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto findOrganizationByOrganizationCode(String code) {
        return modelMapper.map(orgRepo.findOrganizationByOrganizationCode(code), OrganizationDto.class);
    }

    @Override
    public boolean updateOrganizationDisplayName(String displayName, long orgId) {
        return orgRepo.updateOrganizationDisplayNameById(displayName, orgId) == 1;
    }

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        Organization organization = modelMapper.map(organizationDto, Organization.class);
        organization.setOrganizationCode(RandomStringUtils.randomAlphanumeric(10));
        return modelMapper.map(orgRepo.save(organization), OrganizationDto.class);
    }

    @Override
    public void deleteOrganizationById(long orgId) {
        roleService.deleteRolesByOrganizationId(orgId);
        orgRepo.deleteById(orgId);
    }

    @Override
    public List<OrganizationOverviewDetails> getOrganizationOverviewDetails() {
        return orgRepo.getOrganizationOverviewDetails();
    }


}
