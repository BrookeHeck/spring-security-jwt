package com.games.flashcard.service;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.enums.ROLE;

import java.util.List;

public interface RoleService {
    RoleDto addNewRole(long userId, long organizationId, ROLE role);

    boolean deleteRoleById(long roleId);

    boolean deleteRoleByUserId(long userId);

    boolean deleteRoleByOrganizationId(long organizationId);

    List<RoleDto> findRolesByUserId(long userId);

    List<RoleDto> findRolesByOrganizationId(long organizationId);

    List<RoleDto> findRolesByRole(ROLE role);

    List<RoleDto> findRolesByRoleAndOrganizationId(ROLE role, long organizationId);
}
