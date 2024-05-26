package com.games.flashcard.service;

import com.games.flashcard.exception.domain.EmailExistsException;
import com.games.flashcard.exception.domain.UsernameExistsException;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.USER_STATUS;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
public interface UserService {

    AppUser findUserByUsernameOrEmail(String usernameOrEmail);

    boolean deleteUserById(Long userId);

    void changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail);

    void updateUserLastLoginToNow(long userId);

    AppUser registerUser(UserDto userDto, String password) throws UsernameExistsException, EmailExistsException;

    boolean resetPassword(String newPassword, long userId);

    String updateUserPofilePicture(long userId, String username, MultipartFile file) throws IOException;

    String findUsernameByUserId(long userId);

}
