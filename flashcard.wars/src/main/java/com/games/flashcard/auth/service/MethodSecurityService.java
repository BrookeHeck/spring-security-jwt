package com.games.flashcard.auth.service;

import com.games.flashcard.model.enums.PERMISSION;
import com.games.flashcard.model.enums.ROLE;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("authorization")
public class MethodSecurityService {
    public boolean userCanEditRoleAtOrg(MethodSecurityExpressionOperations root, long orgId, ROLE role) {
        Authentication authentication = (Authentication) root.getAuthentication();
        List<String> authorities = authentication.getAuthorities().stream().map(Object::toString).toList();
        return userHasAuthorityAtOrg(orgId, authorities) && userCanEditRole(authorities, role);
    }

    private boolean userHasAuthorityAtOrg(long orgId, List<String> authorities) {
        return authorities.stream().anyMatch(authority -> Long.toString(orgId).equals(authority)
                || authority.equals(ROLE.SUPER.toString()));
    }

    private boolean userCanEditRole(List<String> permissions, ROLE role) {
        return switch (role) {
            case SUPER -> permissions.contains(ROLE.SUPER.toString());
            case ADMIN -> permissions.contains(PERMISSION.MANAGE_ORGANIZATION_ADMINS.toString());
            case TEACHER -> permissions.contains(PERMISSION.ADD_DELETE_TEACHER.toString());
            case TEACHER_ASSISTANT -> permissions.contains(PERMISSION.ADD_DELETE_TEACHER_ASSISTANT.toString());
            case STUDENT -> permissions.contains(PERMISSION.DELETE_STUDENT_ACCOUNT.toString())
                    || permissions.contains(PERMISSION.ACCEPT_STUDENT_ACCESS_REQUEST.toString());
        };
    }
}
