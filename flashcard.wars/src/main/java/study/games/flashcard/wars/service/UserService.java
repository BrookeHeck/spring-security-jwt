package study.games.flashcard.wars.service;

import study.games.flashcard.wars.model.entities.AppUser;
import study.games.flashcard.wars.model.enums.USER_STATUS;

import java.util.List;

public interface UserService {

    AppUser findUserByUsername(String username);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);

    AppUser createUser(AppUser appUser);

    boolean deleteUserById(Long userId);

    AppUser updateUser(AppUser appUser);

    List<AppUser> getAllUsers();

    void changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail);

    void updateUserLastLoginToNow(long userId);

    int resetPassword(String password, long userId);


}
