package com.games.flashcard.auth.service;

import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.enums.PERMISSION;
import com.games.flashcard.model.enums.ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreAuthorizeService {
    public boolean userCanEditRoleAtOrganization(UserPrinciple userPrinciple, long orgId, ROLE role) {
        RoleDto selectedRole = userPrinciple.getAppUser().getSelectedRole();
        return userIsAssignedRoleAtOrganization(selectedRole, orgId) &&
                userCanEditRole(selectedRole.getRole().getPermissions(), role);
    }

    private boolean userIsAssignedRoleAtOrganization(RoleDto role, long organizationId) {
        return role.getRole() == ROLE.SUPER || role.getOrganizationId() == organizationId;
    }

    private boolean userCanEditRole(List<PERMISSION> permissions, ROLE role) {
        return switch (role) {
            case ADMIN -> permissions.contains(PERMISSION.MANAGE_ORGANIZATION_ADMINS);
            case TEACHER -> permissions.contains(PERMISSION.ADD_DELETE_TEACHER);
            case TEACHER_ASSISTANT -> permissions.contains(PERMISSION.ADD_DELETE_TEACHER_ASSISTANT);
            case STUDENT -> permissions.contains(PERMISSION.DELETE_STUDENT_ACCOUNT)
                    || permissions.contains(PERMISSION.ACCEPT_STUDENT_ACCESS_REQUEST);
            default -> false;
        };
    }
}
