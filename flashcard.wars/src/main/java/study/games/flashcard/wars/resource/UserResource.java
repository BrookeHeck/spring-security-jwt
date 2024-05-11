package study.games.flashcard.wars.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserResource {
    private final UserService userService;

    @GetMapping(value = "login")
    public String login() {
        return "LOGIN";
    }

    @PostMapping(value = "register")
    public UserDto register(@RequestBody UserDto userDto) throws Exception {
        UserDto registeredDto = userService.registerUser(userDto);
        registeredDto.setPassword(null);
        return registeredDto;
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
