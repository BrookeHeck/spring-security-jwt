package study.games.flashcard.wars.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.games.flashcard.wars.auth.UserPrinciple;
import study.games.flashcard.wars.models.entities.AppUser;
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
    public UserDetails findByUsernameOrEmail(String usernameOrEmail) {
        AppUser user = userRepo.findAppUserByUsername(usernameOrEmail);
        if(user == null) {
            throw new UsernameNotFoundException("user not found: " + usernameOrEmail);
        }
        user.setLastLoginDate(user.getLastLoginDate());
        user.setLastLoginDate(LocalDate.now());
        return new UserPrinciple(updateUser(user));
    }

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
    public AppUser registerUser(String firstName, String lastName, String username, String email) {
        return null;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return null;
    }


}
