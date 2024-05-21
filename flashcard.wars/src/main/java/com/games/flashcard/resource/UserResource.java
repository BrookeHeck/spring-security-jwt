package com.games.flashcard.resource;

import com.games.flashcard.model.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.*;
import com.games.flashcard.auth.service.AuthenticationService;
import com.games.flashcard.model.dtos.UserDto;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {
    private final AuthenticationService authService;

    @GetMapping(value = "login")
    public ResponseEntity<AppUser> login(@RequestHeader(AUTHORIZATION) String basicAuthHeader) {
        AppUser user = authService.login(basicAuthHeader.split("Basic ")[1]);
        HttpHeaders httpHeaders = authService.getJwtTokenHeader(user);
        return new ResponseEntity<>(user, httpHeaders, OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AppUser> register(@RequestBody UserDto userDto) throws Exception {
        AppUser registeredUser = authService.registerUser(userDto);
        HttpHeaders headers = authService.getJwtTokenHeader(registeredUser);
        return new ResponseEntity<>(registeredUser, headers, CREATED);
    }

    @GetMapping(value = "reset-password/{email}")
    public String resetPassword(@PathVariable String email) {
        throw new LockedException("locked out");
    }

    @GetMapping(value = "home")
    public String home() {
        return "HOME";
    }


}
