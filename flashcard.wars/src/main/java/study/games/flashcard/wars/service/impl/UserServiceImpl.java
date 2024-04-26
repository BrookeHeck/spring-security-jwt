package study.games.flashcard.wars.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.repository.UserRepository;
import study.games.flashcard.wars.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public AppUser getUserByUsername(String username) {
        return userRepo.findAppUserByUsername(username);
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepo.findAppUserByEmail(email);
    }

    @Override
    public AppUser getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public AppUser createUser(AppUser appUser) {
        return userRepo.save(appUser);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        userRepo.deleteById(userId);
        return true;
    }

    @Override
    public AppUser updateUser(AppUser appUser) {
        return userRepo.save(appUser);
    }
}
