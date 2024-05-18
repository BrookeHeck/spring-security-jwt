package study.games.flashcard.wars.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.games.flashcard.wars.auth.AuthenticationService;
import study.games.flashcard.wars.models.dtos.Response;
import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;

import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static study.games.flashcard.wars.auth.SecurityConstants.JWT_HEADER;

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
        return email;
    }

    @GetMapping(value = "home")
    public String home() {
        return "HOME";
    }


}
