package study.games.flashcard.wars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.models.enums.USER_STATUS;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);

    Optional<AppUser> findAppUserByUsernameOrEmail(@Param("USERNAME_EMAIL") String userNameOrEmail);

    AppUser findAppUserByEmail(String email);

    @Modifying
    int updateUserStatus(@Param("STATUS") USER_STATUS status, @Param("USERNAME_EMAIL") String usernameOrEmail);

    @Modifying
    void setLastLoginDate(@Param("LOGIN_DATE") LocalDate loginDate, long userId);
}
