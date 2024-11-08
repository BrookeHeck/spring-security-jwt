package com.games.flashcard.util;

import com.games.flashcard.model.enums.PERMISSION;

import java.util.Arrays;
import java.util.List;

import static com.games.flashcard.model.enums.PERMISSION.*;

public class RolePermissions {
   public static final List<PERMISSION> SUPER_PERMISSIONS = getSuperPermissions();

   public static final List<PERMISSION> ADMIN_PERMISSIONS = Arrays.stream(PERMISSION.values()).toList();

    public static final List<PERMISSION> TEACHER_PERMISSIONS = getTeacherPermissions();

    public static final List<PERMISSION> TEACHER_ASSISTANT_PERMISSIONS = getTeacherAssistantPermissions();

    public static final List<PERMISSION> STUDENT_PERMISSION = getStudentPermissions();

    private static void addLowerPermissionsToPermissionArray(List<PERMISSION> permissions, List<PERMISSION> permissionsToAdd) {
        permissions.addAll(permissionsToAdd);
    }

    private static List<PERMISSION> getSuperPermissions() {
        return List.of(MANAGE_ORGANIZATION, MANAGE_ORGANIZATION_ADMINS);
    }

    private static List<PERMISSION> getAdminPermissions() {
        return List.of(EDIT_ORGANIZATION_DETAILS, ADD_DELETE_TEACHER,
                ADD_DELETE_TEACHER_ASSISTANT, ACCEPT_STUDENT_ACCESS_REQUEST);
    }

    private static List<PERMISSION> getTeacherPermissions() {
        return List.of(ACCEPT_STUDENT_ACCESS_REQUEST, ADD_DELETE_TEACHER_ASSISTANT,
                DELETE_STUDENT_ACCOUNT, CREATE_FLASHCARD,
                DELETE_FLASHCARD, CREATE_FLASHCARD_SET,
                DELETE_FLASHCARD_SSET, START_STOP_GAME);
    }

    private static List<PERMISSION> getTeacherAssistantPermissions() {
        return List.of(CREATE_FLASHCARD_SET, DELETE_FLASHCARD_SSET,
                CREATE_FLASHCARD, DELETE_FLASHCARD, START_STOP_GAME);
    }

    private static List<PERMISSION> getStudentPermissions() {
        return List.of(START_STOP_GAME);
    }

}
