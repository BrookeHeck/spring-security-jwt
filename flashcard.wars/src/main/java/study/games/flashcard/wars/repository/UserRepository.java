package study.games.flashcard.wars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import study.games.flashcard.wars.model.entities.AppUser;
import study.games.flashcard.wars.model.enums.USER_STATUS;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);

    Optional<AppUser> findAppUserByUsernameOrEmail(@Param("USERNAME_EMAIL") String userNameOrEmail);

    AppUser findAppUserByEmail(String email);

    @Modifying
    void updateUserStatus(@Param("STATUS") USER_STATUS status, @Param("USERNAME_EMAIL") String usernameOrEmail);

    @Modifying
    void setLastLoginDate(@Param("LOGIN_DATE") LocalDateTime loginDate, @Param("ID") long userId);
}
