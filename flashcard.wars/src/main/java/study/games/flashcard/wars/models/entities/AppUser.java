package study.games.flashcard.wars.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import study.games.flashcard.wars.models.enums.PERMISSION;
import study.games.flashcard.wars.models.enums.ROLE;
import study.games.flashcard.wars.models.enums.USER_STATUS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "AppUser.findAppUserByUsernameOrEmail",
        query = "select e from AppUser e where e.username = :USERNAME_EMAIL or e.username = :USERNAME_EMAIL")
@NamedQuery(name = "AppUser.updateUserStatus", query = "update AppUser user set user.status = :STATUS where user.id = :ID")
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
    private LocalDate lastLoginDate;
    private LocalDate lastPasswordUpdate;
    @CreationTimestamp
    private LocalDate dateJoined;
    private ROLE role;
    private USER_STATUS status;
    private List<PERMISSION> authorities;
}
