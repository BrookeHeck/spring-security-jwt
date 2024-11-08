package com.games.flashcard.model.enums;

import java.util.List;

import static com.games.flashcard.util.RolePermissions.*;

public enum ROLE {
    SUPER(SUPER_PERMISSIONS, "SUPER"),
    ADMIN(ADMIN_PERMISSIONS, "ADMIN"),
    TEACHER(TEACHER_PERMISSIONS, "TEACHER"),
    TEACHER_ASSISTANT(TEACHER_ASSISTANT_PERMISSIONS, "TEACHER_ASSISTANT"),
    STUDENT(STUDENT_PERMISSION, "STUDENT");

    private final List<PERMISSION> permissions;
    private final String name;

    ROLE(List<PERMISSION> permissions, String name) {
        this.permissions = permissions;
        this.name = name;
    }

    public List<PERMISSION> getPermissions() {return permissions;}
    public String getName() {return name;}

}
