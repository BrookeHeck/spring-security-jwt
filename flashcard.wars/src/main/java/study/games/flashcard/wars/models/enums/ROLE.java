package study.games.flashcard.wars.models.enums;

import study.games.flashcard.wars.RolePermissions;

import java.util.List;

import static study.games.flashcard.wars.RolePermissions.*;

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
