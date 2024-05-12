package study.games.flashcard.wars.auth;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.games.flashcard.wars.exception.domain.EmailExistsException;
import study.games.flashcard.wars.exception.domain.UsernameExistsException;
import study.games.flashcard.wars.models.dtos.UserDto;
import study.games.flashcard.wars.models.entities.AppUser;
import study.games.flashcard.wars.models.enums.USER_STATUS;
import study.games.flashcard.wars.repository.UserRepository;

import java.time.LocalDate;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AppUser login(String authenticationHeader) {
        String decodedAuthentication = new String(Base64.getDecoder().decode(authenticationHeader));
        String[] usernameAndPassword = decodedAuthentication.split(":");
        String username = usernameAndPassword[0];
        String password = usernameAndPassword[1];
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        AppUser user = userRepository.findAppUserByUsernameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        user.setLastLoginDate(LocalDate.now());
        return user;
    }

    public String generateJwtToken(AppUser appUser) {
        UserPrinciple userPrinciple = new UserPrinciple(appUser);
        return jwtService.generateJwt(userPrinciple);
    }

    public AppUser registerUser(UserDto userDto)
            throws UsernameExistsException, EmailExistsException, NullPointerException {
        String username = userDto.getUsername();
        if(StringUtils.isBlank(username) || userNameAlreadyExists(username))
            throw new UsernameExistsException(username + " is already being used.");
        String email = userDto.getEmail();
        if(StringUtils.isBlank(email) || emailAlreadyExists(email))
            throw new EmailExistsException(email + " is already being used.");
        if(StringUtils.isBlank(userDto.getFirstName()) || StringUtils.isBlank(userDto.getFirstName()))
            throw new NullPointerException("Name cannot be blank on registration.");
        if(userDto.getRole() == null) throw new NullPointerException("A role is needed for registration.");
        AppUser appUser = createNewAppUser(userDto);
        return userRepository.save(appUser);
    }

    private boolean userNameAlreadyExists(String userName) {
        return userRepository.findAppUserByUsername(userName) != null;
    }

    private boolean emailAlreadyExists(String email) {
        return userRepository.findAppUserByEmail(email) != null;
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

    private AppUser createNewAppUser(UserDto userDto) {
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
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        return appUser;
    }
}
