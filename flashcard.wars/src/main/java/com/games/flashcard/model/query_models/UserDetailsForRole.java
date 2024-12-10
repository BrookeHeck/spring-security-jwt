package com.games.flashcard.model.query_models;

import com.games.flashcard.model.enums.USER_STATUS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsForRole implements Serializable {
    private long roleId;
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateAssignedRole;
    private USER_STATUS userStatus;

    public UserDetailsForRole(long roleId, long userId, String firstName, String lastName, String email, LocalDateTime dateAssignedRole, USER_STATUS userStatus) {
        this.roleId = roleId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateAssignedRole = dateAssignedRole;
        this.userStatus = userStatus;
    }
}
