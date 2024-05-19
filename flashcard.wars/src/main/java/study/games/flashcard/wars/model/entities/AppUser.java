package study.games.flashcard.wars.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import study.games.flashcard.wars.model.enums.PERMISSION;
import study.games.flashcard.wars.model.enums.ROLE;
import study.games.flashcard.wars.model.enums.USER_STATUS;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "AppUser.findAppUserByUsernameOrEmail",
        query = "select user from AppUser user where user.username = :USERNAME_EMAIL or user.email = :USERNAME_EMAIL")
@NamedQuery(name = "AppUser.updateUserStatus",
        query = "update AppUser user set user.status = :STATUS where user.username = :USERNAME_EMAIL or user.email = :USERNAME_EMAIL")
@NamedQuery(name = "AppUser.setLastLoginDate", query = "update AppUser user set user.lastLoginDate = :LOGIN_DATE where user.id = :ID")
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
    private ROLE role;
    @Enumerated(EnumType.STRING)
    private USER_STATUS status;
    @Transient
    private List<PERMISSION> authorities;
}
