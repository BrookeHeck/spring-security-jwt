package com.games.flashcard.model.dtos;

import com.games.flashcard.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Set<RoleDto> roles;
    private String profileImageUrl;
    private LocalDateTime lastLoginDate;
    private LocalDateTime dateJoined;
}
