package study.games.flashcard.wars.service;

import org.springframework.security.core.userdetails.UserDetails;
import study.games.flashcard.wars.exception.domain.EmailExistsException;
import study.games.flashcard.wars.exception.domain.UsernameExistsException;
import study.games.flashcard.wars.models.entities.AppUser;

import java.util.List;

public interface UserService {
    UserDetails findByUsernameOrEmail(String usernameOrEmail);

    AppUser findUserByUsername(String username);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);

    AppUser registerUser(String firstName, String lastName, String username, String email) throws Exception;

    List<AppUser> getAllUsers();


}
