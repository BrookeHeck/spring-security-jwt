package com.games.flashcard.auth.service;

import com.games.flashcard.auth.SecurityConstants;
import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.exception.domain.EmailExistsException;
import com.games.flashcard.exception.domain.UsernameExistsException;
import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.games.flashcard.service.EmailService;

import javax.mail.MessagingException;
import java.util.Base64;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public UserDto login(String authenticationHeader) {
        String encodedHeader = removeBasicPrefixFromAuthHeader(authenticationHeader);
        String[] usernameAndPassword =  new String(Base64.getDecoder().decode(encodedHeader)).split(":");
        String username = usernameAndPassword[0];
        String password = usernameAndPassword[1];
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return userService.findUserByUsernameOrEmail(username);
    }

    public UserDto selectRoleOrgPair(String header, ROLE role, long organizationId) throws IllegalAccessException {
        String username = jwtService.getSubject(removeBasicPrefixFromAuthHeader(header));
        UserDto appUser = userService.findUserByUsernameOrEmail(username);
        boolean userIsAssignedToRole = userIsAssignedRoleOrgPair(appUser.getRoles(), role, organizationId);
        if(userIsAssignedToRole) {
            appUser.setAuthorities(role.getPermissions());
        } else {
            throw new IllegalAccessException("User does not have access to role at org");
        }
        return appUser;
    }

    public HttpHeaders getJwtTokenHeader(UserDto user) {
        String jwt = generateJwtToken(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.JWT_HEADER, jwt);
        return httpHeaders;
    }

    private String generateJwtToken(UserDto appUser) {
        UserPrinciple userPrinciple = new UserPrinciple(appUser, null);
        return jwtService.generateJwt(userPrinciple);
    }

    public UserDto registerUser(UserDto userDto)
            throws UsernameExistsException, EmailExistsException, NullPointerException {
        String password = generatePassword(userDto.getPassword());
        return userService.registerUser(userDto, password);
    }

    public boolean resetPassword(String authHeader, String newPassword) throws MessagingException {
        UserDto appUser = login(authHeader);
        boolean hasPasswordReset = userService.resetPassword(generatePassword(newPassword), appUser.getId());
        if(hasPasswordReset) {
//            emailService.sendPasswordResetEmail(appUser.getFirstName(), appUser.getEmail());
        }
        return hasPasswordReset;
    }

    private String generatePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String removeBasicPrefixFromAuthHeader(String authHeader) {
        return authHeader.split("Basic ")[1];
    }

    private boolean userIsAssignedRoleOrgPair(Set<RoleDto> roles, ROLE role, long organizationId) {
        for(RoleDto index : roles) {
            if(index.getRole() == role && index.getOrganizationId() == organizationId) {
                return true;
            }
        }
        return false;
    }

}
