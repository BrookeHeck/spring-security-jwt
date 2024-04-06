package study.games.flashcard.wars.models.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import study.games.flashcard.wars.enums.ROLE;
import study.games.flashcard.wars.enums.USER_STATUS;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    private Long id;
    private String userId;
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
    private Date lastLoginDate;
    @CreationTimestamp
    private Date dateJoined;
    private ROLE role;
    private USER_STATUS status;
}
