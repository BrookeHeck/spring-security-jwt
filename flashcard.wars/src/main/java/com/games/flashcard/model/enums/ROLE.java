package com.games.flashcard.model.enums;

import java.util.List;

import static com.games.flashcard.RolePermissions.*;

public enum ROLE {
    STUDENT(STUDENT_PERMISSION),
    TEACHER(TEACHER_PERMISSIONS),
    ADMIN(ADMIN_PERMISSIONS),
    TEACHER_ASSISTANT(TEACHER_ASSISTANT_PERMISSIONS);

    private final List<PERMISSION> permissions;

    ROLE(List<PERMISSION> permissions) {
        this.permissions = permissions;
    }

    public List<PERMISSION> getPermissions() {return permissions;}

}
