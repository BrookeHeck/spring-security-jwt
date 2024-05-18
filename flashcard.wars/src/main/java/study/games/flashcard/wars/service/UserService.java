package study.games.flashcard.wars.service;

import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.models.enums.USER_STATUS;

import java.util.List;

public interface UserService {

    AppUser findUserByUsername(String username);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);

    List<AppUser> getAllUsers();

    boolean changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail);

    void updateUserLastLoginToNow(long userId);


}
