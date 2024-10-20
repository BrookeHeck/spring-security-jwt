package com.games.flashcard.service.impl;

import com.games.flashcard.exception.domain.EmailExistsException;
import com.games.flashcard.exception.domain.UsernameExistsException;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.games.flashcard.model.enums.USER_STATUS;
import com.games.flashcard.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static com.games.flashcard.util.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public AppUser findUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepo.findAppUserByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmail));
    }

    @Override
    public boolean deleteUserById(Long userId) {
        userRepo.deleteById(userId);
        return true;
    }

    @Override
    public void changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail) {
        userRepo.updateUserStatus(userStatus, usernameOrEmail);
    }

    @Override
    public void updateUserLastLoginToNow(long userId) {
        userRepo.setLastLoginDate(LocalDateTime.now(), userId);
    }

    @Override
    public AppUser registerUser(UserDto userDto, String password) throws UsernameExistsException, EmailExistsException {
        String username = userDto.getUsername();
        if(StringUtils.isBlank(username) || userNameAlreadyExists(username))
            throw new UsernameExistsException(username + " is already being used.");
        String email = userDto.getEmail();
        if(StringUtils.isBlank(email) || emailAlreadyExists(email))
            throw new EmailExistsException(email + " is already being used.");
        if(StringUtils.isBlank(userDto.getFirstName()) || StringUtils.isBlank(userDto.getFirstName()))
            throw new NullPointerException("Name cannot be blank on registration.");
        if(userDto.getRole() == null) throw new NullPointerException("A role is needed for registration.");
        AppUser appUser = createNewAppUser(userDto, password);
        return userRepo.save(appUser);
    }

    @Override
    public boolean resetPassword(String userPassword, long userId) {
        return this.userRepo.resetPassword(userPassword, userId) != 0;
    }

    @Override
    public String  updateUserPofilePicture(long userId, String username, MultipartFile file) throws IOException {
        String userImageUrl = saveImageToFolder(file, username);
        userRepo.updateUserProfilePictureUrlByUserId(userImageUrl, userId);
        return userImageUrl;
    }

    @Override
    public String findUsernameByUserId(long userId) {return userRepo.findUsernameByUserId(userId);}

    private boolean userNameAlreadyExists(String userName) {
        return userRepo.findAppUserByUsername(userName) != null;
    }

    private boolean emailAlreadyExists(String email) {
        return userRepo.findAppUserByEmail(email) != null;
    }

    private String generateUserId() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private AppUser createNewAppUser(UserDto userDto, String password) {
        AppUser appUser = new AppUser();
        appUser.setLastLoginDate(LocalDateTime.now());
        appUser.setUserId(generateUserId());
        appUser.setEmail(userDto.getEmail());
        appUser.setStatus(USER_STATUS.ACTIVE);
        appUser.setPassword(password);
        appUser.setUsername(userDto.getUsername());
        appUser.setLastPasswordUpdate(LocalDateTime.now());
        appUser.setAuthorities(userDto.getRole().getRole().getPermissions());
        appUser.setProfileImageUrl(getTemporaryProfileImageUrl(userDto.getUsername()));
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        return appUser;
    }

    private String saveImageToFolder(MultipartFile profileImage, String username) throws IOException {
        if(profileImage != null) {
            Path userFolder = Paths.get(USER_FOLDER + username).toAbsolutePath().normalize();
            if(!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                log.info(DIRECTORY_CREATED + username);
            }
            Files.deleteIfExists(Paths.get(userFolder + username + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(username + JPG_EXTENSION), REPLACE_EXISTING);
            log.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
            return getProfileImageUrl(username);
        }
        return null;
    }

    private String getTemporaryProfileImageUrl(String username) {
        return TEMP_PROFILE_IMAGE_BASE_URL + username;
    }

    private String getProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username
        + "/" + username + JPG_EXTENSION).toUriString();
    }
}
