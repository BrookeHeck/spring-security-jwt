package study.games.flashcard.wars.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.games.flashcard.wars.auth.UserPrinciple;
import study.games.flashcard.wars.exception.domain.EmailExistsException;
import study.games.flashcard.wars.exception.domain.UsernameExistsException;
import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.models.enums.ROLE;
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
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

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
    public UserDto registerUser(UserDto userDto)
            throws UsernameExistsException, EmailExistsException, NullPointerException {
        if(StringUtils.isBlank(userDto.getUsername()) || userNameAlreadyExists(userDto.getUsername()))
            throw new UsernameExistsException(userDto.getUsername() + " is already being used.");
        if(StringUtils.isBlank(userDto.getEmail()) || emailAlreadyExists(userDto.getEmail()))
            throw new EmailExistsException(userDto.getEmail() + " is already being used.");
        if(StringUtils.isBlank(userDto.getFirstName()) || StringUtils.isBlank(userDto.getFirstName()))
            throw new NullPointerException("Name cannot be blank on registration.");
        if(userDto.getRole() == null) throw new NullPointerException("A role is needed for registration.");
        AppUser appUser = new AppUser();
        appUser.setLastLoginDate(LocalDate.now());
        appUser.setUserId(generateUserId());
        appUser.setEmail(userDto.getEmail());
        appUser.setRole(userDto.getRole());
        appUser.setStatus(USER_STATUS.ACTIVE);
        appUser.setPassword(generatePassword(userDto.getPassword()));
        appUser.setUsername(userDto.getUsername());
        appUser.setDateJoined(LocalDate.now());
        appUser.setLastPasswordUpdate(LocalDate.now());
        appUser.setAuthorities(userDto.getRole().getPermissions());
        appUser.setProfileImageUrl(getTemporaryProfileImage());
        return modelMapper.map(createUser(appUser), UserDto.class);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    private boolean userNameAlreadyExists(String userName) {
        return findUserByUsername(userName) != null;
    }

    private boolean emailAlreadyExists(String email) {
        return findUserByEmail(email) != null;
    }

    private String generateUserId() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generatePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String getTemporaryProfileImage() {
        return "https://placehold.co/100x100";
//        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/image/profile/temp").toString;
    }


}
