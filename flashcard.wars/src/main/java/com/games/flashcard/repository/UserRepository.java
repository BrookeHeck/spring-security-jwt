package com.games.flashcard.repository;

import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.games.flashcard.model.enums.USER_STATUS;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);

    @Query("select user from AppUser user where user.username = :USERNAME_EMAIL or user.email = :USERNAME_EMAIL")
    Optional<AppUser> findAppUserByUsernameOrEmail(@Param("USERNAME_EMAIL") String userNameOrEmail);

    AppUser findAppUserByEmail(String email);

    @Modifying
    @Query("update AppUser user set user.status = :STATUS where user.username = :USERNAME_EMAIL or user.email = :USERNAME_EMAIL")
    void updateUserStatus(@Param("STATUS") USER_STATUS status, @Param("USERNAME_EMAIL") String usernameOrEmail);

    @Modifying
    @Query("update AppUser user set user.lastLoginDate = :LOGIN_DATE where user.id = :ID")
    void setLastLoginDate(@Param("LOGIN_DATE") LocalDateTime loginDate, @Param("ID") long userId);

    @Modifying
    @Query("update AppUser user set user.password = :PASSWORD, user.lastPasswordUpdate = current_timestamp where user.id = :ID")
    int resetPassword(@Param("PASSWORD") String password, @Param("ID") long userId);

    @Modifying
    @Query("update AppUser  user set user.profileImageUrl = :IMAGE_URL where user.id = :ID")
    void updateUserProfilePictureUrlByUserId(@Param("IMAGE_URL") String imageUrl, @Param("ID") long userId);

    @Query("select user.username from AppUser user where user.id = :ID")
    String findUsernameByUserId(@Param("ID") long userId);

    @Modifying
    @Query("update AppUser user set user.role = :ROLE where user.id = :ID")
    int changeUserRole(@Param("ROLE")ROLE role, @Param("ID") long userId);
}
