package com.games.flashcard.model.query_models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFirstNameAndEmail {
    private String firstName;
    private String email;

    public UserFirstNameAndEmail(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }
}
