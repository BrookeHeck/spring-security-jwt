package com.games.flashcard.resource;

import com.games.flashcard.model.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.*;
import com.games.flashcard.auth.service.AuthenticationService;
import com.games.flashcard.model.dtos.UserDto;

import javax.mail.MessagingException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {
    private final AuthenticationService authService;

    @GetMapping(value = "login")
    public ResponseEntity<AppUser> login(@RequestHeader(AUTHORIZATION) String basicAuthHeader) {
        AppUser user = authService.login(basicAuthHeader);
        HttpHeaders httpHeaders = authService.getJwtTokenHeader(user);
        return new ResponseEntity<>(user, httpHeaders, OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AppUser> register(@RequestBody UserDto userDto) throws Exception {
        AppUser registeredUser = authService.registerUser(userDto);
        HttpHeaders headers = authService.getJwtTokenHeader(registeredUser);
        return new ResponseEntity<>(registeredUser, headers, CREATED);
    }

    @GetMapping(value = "reset-password/{userId}")
    public ResponseEntity<Boolean> resetPassword(@PathVariable long userId, @RequestBody String newPassword,
                                @RequestHeader(AUTHORIZATION) String basicAuthHeader) throws MessagingException {
        boolean hasPasswordReset = authService.resetPassword(basicAuthHeader, newPassword, userId);
        HttpStatus status = hasPasswordReset ? OK : INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(hasPasswordReset, status);
    }
}
