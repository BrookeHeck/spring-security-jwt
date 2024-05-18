package study.games.flashcard.wars.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.models.enums.USER_STATUS;
import study.games.flashcard.wars.repository.UserRepository;
import study.games.flashcard.wars.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepo.findAppUserByUsername(username);
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepo.findAppUserByEmail(email);
    }

    @Override
    public AppUser findUserById(Long id) {
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

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail) {
        int rows = userRepo.updateUserStatus(userStatus, usernameOrEmail);
        return rows != 0;
    }

    @Override
    public void updateUserLastLoginToNow(long userId) {
        userRepo.setLastLoginDate(LocalDate.now(), userId);
    }


}
