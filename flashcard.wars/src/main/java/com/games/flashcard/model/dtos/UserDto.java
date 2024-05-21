package com.games.flashcard.model.dtos;

import com.games.flashcard.model.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

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
    private ROLE role;
    private String profileImageUrl;
    private LocalDate lastLoginDate;
    private LocalDate dateJoined;
}
