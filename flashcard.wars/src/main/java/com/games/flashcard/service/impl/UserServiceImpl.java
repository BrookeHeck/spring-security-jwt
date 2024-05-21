package com.games.flashcard.service.impl;

import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.games.flashcard.model.enums.USER_STATUS;
import com.games.flashcard.repository.UserRepository;

import java.time.LocalDateTime;
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
    public void changeAccountStatus(USER_STATUS userStatus, String usernameOrEmail) {
        userRepo.updateUserStatus(userStatus, usernameOrEmail);
    }

    @Override
    public void updateUserLastLoginToNow(long userId) {
        userRepo.setLastLoginDate(LocalDateTime.now(), userId);
    }


}
