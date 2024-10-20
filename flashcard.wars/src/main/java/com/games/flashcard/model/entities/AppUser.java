package com.games.flashcard.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import com.games.flashcard.model.enums.PERMISSION;
import com.games.flashcard.model.enums.USER_STATUS;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private LocalDateTime lastLoginDate;
    private LocalDateTime lastPasswordUpdate;
    @CreationTimestamp
    private LocalDateTime dateJoined;
    @Enumerated(EnumType.STRING)
    private USER_STATUS status;
    @Transient
    private List<PERMISSION> authorities;
    @Transient
    private Role role;
    @OneToMany
    private List<Role> roles;
}
