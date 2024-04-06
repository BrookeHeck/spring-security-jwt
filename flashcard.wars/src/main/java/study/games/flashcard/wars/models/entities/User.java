package study.games.flashcard.wars.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import study.games.flashcard.wars.enums.ROLE;
import study.games.flashcard.wars.enums.USER_STATUS;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
    private LocalDate lastLoginDate;
    private LocalDate lastPasswordUpdate;
    @CreationTimestamp
    private LocalDate dateJoined;
    private ROLE role;
    private USER_STATUS status;
}
