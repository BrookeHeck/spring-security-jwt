package study.games.flashcard.wars.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userId;
    private String username;
    private String email;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date dateJoined;
}
