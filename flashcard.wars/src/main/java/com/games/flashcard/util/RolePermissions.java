package com.games.flashcard.util;

import com.games.flashcard.model.enums.PERMISSION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RolePermissions {
   public static final List<PERMISSION> ADMIN_PERMISSIONS = Arrays.stream(PERMISSION.values()).toList();

    public static final List<PERMISSION> TEACHER_PERMISSIONS = getTeacherPermissions();

    public static final List<PERMISSION> TEACHER_ASSISTANT_PERMISSIONS = getTeacherAssistantPermissions();

    public static final List<PERMISSION> STUDENT_PERMISSION = getStudentPermissions();

    private static void addLowerPermissionsToPermissionArray(List<PERMISSION> permissions, List<PERMISSION> permissionsToAdd) {
        permissions.addAll(permissionsToAdd);
    }

    private static List<PERMISSION> getStudentPermissions() {
        List<PERMISSION> permissions = new ArrayList<>();
        permissions.add(PERMISSION.CREATE_FLASHCARD_QUESTIONS);
        permissions.add(PERMISSION.VIEW_FLASHCARD_QUESTIONS);
        return permissions;
    }

    private static List<PERMISSION> getTeacherAssistantPermissions() {
        List<PERMISSION> permissions = new ArrayList<>();
        addLowerPermissionsToPermissionArray(permissions, getStudentPermissions());
        permissions.add(PERMISSION.DELETE_FLASHCARD_QUESTIONS);
        permissions.add(PERMISSION.EDIT_FLASHCARD_QUESTIONS);
        permissions.add(PERMISSION.CREATE_STUDENT_PROFILES);
        permissions.add(PERMISSION.VIEW_STUDENT_PROFILES);
        permissions.add(PERMISSION.DELETE_STUDENT_PROFILES);
        permissions.add(PERMISSION.CREATE_GROUP);
        permissions.add(PERMISSION.VIEW_GROUP);
        permissions.add(PERMISSION.EDIT_GROUP);
        permissions.add(PERMISSION.DELETE_GROUP);
        return permissions;
    }

    private static List<PERMISSION> getTeacherPermissions() {
        List<PERMISSION> permissions = new ArrayList<>();
        addLowerPermissionsToPermissionArray(permissions, getStudentPermissions());
        addLowerPermissionsToPermissionArray(permissions, getTeacherAssistantPermissions());
        permissions.add(PERMISSION.VIEW_TEACHER_ASSISTANT_PROFILES);
        permissions.add(PERMISSION.EDIT_TEACHER_ASSISTANT_PROFILES);
        permissions.add(PERMISSION.CREATE_TEACHER_ASSISTANT_PROFILES);
        permissions.add(PERMISSION.DELETE_TEACHER_ASSISTANT_PROFILES);
        permissions.add(PERMISSION.VIEW_ORGANIZATION);
        permissions.add(PERMISSION.EDIT_ORGANIZATION);
        permissions.add(PERMISSION.CREATE_ORGANIZATION);
        permissions.add(PERMISSION.DELETE_ORGANIZATION);
        return permissions;
    }
}
