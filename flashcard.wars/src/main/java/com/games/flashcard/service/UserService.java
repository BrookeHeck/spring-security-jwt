package com.games.flashcard.service;

import com.games.flashcard.exception.domain.EmailExistsException;
import com.games.flashcard.exception.domain.UsernameExistsException;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.USER_STATUS;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface UserService {

    AppUser findUserByUsernameOrEmail(String usernameOrEmail);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);

    List<AppUser> getAllUsers();

    void changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail);

    void updateUserLastLoginToNow(long userId);

    AppUser registerUser(UserDto userDto, String password) throws UsernameExistsException, EmailExistsException;

    boolean resetPassword(String newPassword, long userId);

}
