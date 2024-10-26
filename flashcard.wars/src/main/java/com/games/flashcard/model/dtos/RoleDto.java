package com.games.flashcard.model.dtos;

import com.games.flashcard.model.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable {
    private long id;
    private ROLE role;
    private long organizationId;
    private long userId;
}
