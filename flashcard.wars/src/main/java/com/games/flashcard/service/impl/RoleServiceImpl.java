package com.games.flashcard.service.impl;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.entities.Organization;
import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.repository.RoleRepository;
import com.games.flashcard.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto addNewRole(RoleDto roleDto) {
        return null;
    }

    @Override
    public boolean deleteRoleById(long roleId) {
        return roleRepo.deleteRoleById(roleId);
    }

    @Override
    public boolean deleteRolesByUserId(long userId) {
        return roleRepo.deleteRolesByUserId(userId);
    }

    @Override
    public boolean deleteRolesByOrganizationId(long organizationId) {
        return roleRepo.deleteRolesByOrganizationId(organizationId);
    }

    @Override
    public List<RoleDto> findRolesByUserId(long userId) {
        return mapRoleRecordsToRoleDtos(roleRepo.findRolesByUserId(userId));
    }

    @Override
    public List<RoleDto> findRolesByOrganizationId(long organizationId) {
        return mapRoleRecordsToRoleDtos(roleRepo.findRolesByOrganizationId(organizationId));
    }

    @Override
    public List<RoleDto> findRolesByRole(ROLE role) {
        return mapRoleRecordsToRoleDtos(roleRepo.findRolesByRole(role));
    }

    @Override
    public List<RoleDto> findRolesByRoleAndOrganizationId(ROLE role, long organizationId) {
        return mapRoleRecordsToRoleDtos(roleRepo.findRolesByRoleAndOrganizationId(role, organizationId));

    }

    private List<RoleDto> mapRoleRecordsToRoleDtos(List<Role> roleRecords) {
        return roleRecords.stream().map(roleRecord ->
                modelMapper.map(roleRecord, RoleDto.class)).collect(Collectors.toList());
    }
}
