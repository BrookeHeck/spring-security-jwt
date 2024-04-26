package study.games.flashcard.wars.service;

import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;

public interface UserService {
    AppUser getUserByUsername(String username);

    AppUser getUserByEmail(String email);

    AppUser getUserById(Long id);

    AppUser createUser(UserDto userDto);

    boolean deleteUserById(Long userId);

    AppUser updateUser(UserDto userDto);
}
