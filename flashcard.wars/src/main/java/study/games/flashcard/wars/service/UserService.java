package study.games.flashcard.wars.service;

import org.springframework.security.core.userdetails.UserDetails;
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

    AppUser registerUser(String firstName, String lastName, String username, String email, ROLE role, String password) throws Exception;

    List<AppUser> getAllUsers();


}
