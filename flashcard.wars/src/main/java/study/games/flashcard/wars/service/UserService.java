package study.games.flashcard.wars.service;

import org.springframework.security.core.userdetails.UserDetails;
import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.models.enums.ROLE;

import java.util.List;

public interface UserService {
    UserDetails findByUsernameOrEmail(String usernameOrEmail);

    AppUser findUserByUsername(String username);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);

    UserDto registerUser(UserDto userDto) throws Exception;

    List<AppUser> getAllUsers();


}
