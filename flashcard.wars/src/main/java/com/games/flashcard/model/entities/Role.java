package com.games.flashcard.model.entities;

import com.games.flashcard.model.enums.ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
