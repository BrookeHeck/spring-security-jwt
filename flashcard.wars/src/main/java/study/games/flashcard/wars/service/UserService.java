package study.games.flashcard.wars.service;

import org.springframework.security.core.userdetails.UserDetails;
import study.games.flashcard.wars.models.entities.AppUser;

public interface UserService {
    UserDetails getUserByUsername(String username);

    AppUser getUserByEmail(String email);

    AppUser getUserById(Long id);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);
}
