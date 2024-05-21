package com.games.flashcard.service;

import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.USER_STATUS;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface UserService {

    AppUser findUserByUsername(String username);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);

    List<AppUser> getAllUsers();

    void changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail);

    void updateUserLastLoginToNow(long userId);

}
