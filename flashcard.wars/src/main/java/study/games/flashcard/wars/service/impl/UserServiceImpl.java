package study.games.flashcard.wars.service.impl;

import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public AppUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return null;
    }

    @Override
    public AppUser getUserById(Long id) {
        return null;
    }

    @Override
    public AppUser createUser(UserDto userDto) {
        return null;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        return false;
    }

    @Override
    public AppUser updateUser(UserDto userDto) {
        return null;
    }
}
