package com.games.flashcard.service;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.model.query_models.ManageAdminDetails;

import java.util.List;

public interface RoleService {
    RoleDto addNewRole(RoleDto roleDto);

    void deleteRoleById(long roleId);

    boolean deleteRolesByUserId(long userId);

    void deleteRolesByOrganizationId(long organizationId);

    List<RoleDto> findRolesByUserId(long userId);

    List<RoleDto> findRolesByOrganizationId(long organizationId);

    List<RoleDto> findRolesByRole(ROLE role);

    List<RoleDto> findRolesByRoleAndOrganizationId(ROLE role, long organizationId);

    List<ManageAdminDetails> getManageAdminDetailsForOrganization(long organizationId);
}
