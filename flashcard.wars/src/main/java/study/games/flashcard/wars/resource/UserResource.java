package study.games.flashcard.wars.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import study.games.flashcard.wars.auth.AuthenticationService;
import study.games.flashcard.wars.models.dtos.Response;
import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.service.UserService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {
    private final AuthenticationService authService;

    @GetMapping(value = "login")
    public Response<AppUser> login() {

        return null;
    }

    @PostMapping(value = "register")
    public Response<AppUser> register(@RequestBody UserDto userDto) throws Exception {
        AppUser registeredDto = authService.registerUser(userDto);
//        registeredDto.setPassword(null);
        return Response.<AppUser>builder()
                .timeStamp(LocalDateTime.now())
                .httpStatusCode(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .data(registeredDto)
                .message("registration successful")
                .build();
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
