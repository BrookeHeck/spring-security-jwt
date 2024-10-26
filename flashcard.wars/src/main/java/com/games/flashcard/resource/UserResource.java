package com.games.flashcard.resource;

import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.games.flashcard.auth.service.AuthenticationService;
import com.games.flashcard.model.dtos.UserDto;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {
    private final AuthenticationService authService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "login")
    public ResponseEntity<UserDto> login(@RequestHeader(AUTHORIZATION) String basicAuthHeader) {
        AppUser user = authService.login(basicAuthHeader);
        HttpHeaders httpHeaders = authService.getJwtTokenHeader(user);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, httpHeaders, OK);
    }

    @GetMapping(value = "select-role-org/{role}/{orgId}")
    public ResponseEntity<AppUser> selectRoleOrgPair(@RequestHeader(AUTHORIZATION) String token,
                                                    @PathVariable ROLE role, @PathVariable long orgId) throws IllegalAccessException {
        AppUser user = authService.selectRoleOrgPair(token, role, orgId);
        HttpHeaders httpHeaders = authService.getJwtTokenHeader(user);
        return new ResponseEntity<>(user, httpHeaders, OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AppUser> register(@RequestBody UserDto userDto) throws Exception {
        log.info("Registration attempt for username: " + userDto.getUsername(), " and email: " + userDto.getEmail());
        AppUser registeredUser = authService.registerUser(userDto);
        HttpHeaders headers = authService.getJwtTokenHeader(registeredUser);
        log.info("User created with username: " + registeredUser.getUsername() + " and email: " + registeredUser.getEmail());
        return new ResponseEntity<>(registeredUser, headers, CREATED);
    }

    @GetMapping(value = "reset-password")
    public ResponseEntity<Boolean> resetPassword( @RequestBody String newPassword,
                                @RequestHeader(AUTHORIZATION) String basicAuthHeader) throws MessagingException {
        boolean hasPasswordReset = authService.resetPassword(basicAuthHeader, newPassword);
        HttpStatus status = hasPasswordReset ? OK : INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(hasPasswordReset, status);
    }

    @PostMapping(value = "update-profile-pic/{userId}")
    public ResponseEntity<String> updateUserProfilePicture(@RequestBody MultipartFile profilePicture, @PathVariable long userId) throws IOException {
        String username = userService.findUsernameByUserId(userId);
        String profilePictureUpdatedUrl = userService.updateUserPofilePicture(userId, username, profilePicture);
        return new ResponseEntity<>(profilePictureUpdatedUrl, OK);
    }
}
