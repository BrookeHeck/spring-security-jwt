package com.games.flashcard.model.enums;

import java.util.List;

import static com.games.flashcard.util.RolePermissions.*;

public enum ROLE {
    STUDENT(STUDENT_PERMISSION, "STUDENT"),
    TEACHER(TEACHER_PERMISSIONS, "TEACHER"),
    ADMIN(ADMIN_PERMISSIONS, "ADMIN"),
    TEACHER_ASSISTANT(TEACHER_ASSISTANT_PERMISSIONS, "TEACHER_ASSISTANT");

    private final List<PERMISSION> permissions;
    private final String name;

    ROLE(List<PERMISSION> permissions, String name) {
        this.permissions = permissions;
        this.name = name;
    }

    public List<PERMISSION> getPermissions() {return permissions;}
    public String getName() {return name;}

}
