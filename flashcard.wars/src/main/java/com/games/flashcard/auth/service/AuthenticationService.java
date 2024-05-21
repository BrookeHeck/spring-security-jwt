package com.games.flashcard.auth.service;

import com.games.flashcard.auth.SecurityConstants;
import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.exception.domain.EmailExistsException;
import com.games.flashcard.exception.domain.UsernameExistsException;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.games.flashcard.model.enums.USER_STATUS;
import com.games.flashcard.service.EmailService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public AppUser login(String authenticationHeader) {
        String[] usernameAndPassword =  new String(Base64.getDecoder().decode(authenticationHeader)).split(":");
        String username = usernameAndPassword[0];
        String password = usernameAndPassword[1];
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return userRepository.findAppUserByUsernameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public HttpHeaders getJwtTokenHeader(AppUser user) {
        String jwt = generateJwtToken(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.JWT_HEADER, jwt);
        return httpHeaders;
    }

    private String generateJwtToken(AppUser appUser) {
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

    public boolean resetPassword(String authHeader, String newPassword, long userId) throws MessagingException {
        AppUser appUser = login(authHeader);
        boolean hasPasswordReset = userRepository.resetPassword(generatePassword(newPassword), userId) != 0;
        if(hasPasswordReset) {
            emailService.sendPasswordResetEmail(appUser.getFirstName(), appUser.getEmail());
        }
        return hasPasswordReset;
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
        appUser.setLastLoginDate(LocalDateTime.now());
        appUser.setUserId(generateUserId());
        appUser.setEmail(userDto.getEmail());
        appUser.setRole(userDto.getRole());
        appUser.setStatus(USER_STATUS.ACTIVE);
        appUser.setPassword(generatePassword(userDto.getPassword()));
        appUser.setUsername(userDto.getUsername());
        appUser.setLastPasswordUpdate(LocalDateTime.now());
        appUser.setAuthorities(userDto.getRole().getPermissions());
        appUser.setProfileImageUrl(getTemporaryProfileImage());
        appUser.setFirstName(userDto.getFirstName());
        appUser.setLastName(userDto.getLastName());
        return appUser;
    }
}
