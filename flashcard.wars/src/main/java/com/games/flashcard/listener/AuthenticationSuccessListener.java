package com.games.flashcard.listener;

import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import com.games.flashcard.auth.service.LoginAttemptService;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationSuccessListener {
    private final LoginAttemptService loginAttemptService;
    private final UserService userService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Object principle = authenticationSuccessEvent.getAuthentication().getPrincipal();
        if(principle instanceof UserPrinciple userPrinciple) {
            AppUser user = userPrinciple.getAppUser();
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
            loginAttemptService.evictUserFromLoginAttemptCache(user.getEmail());
            userService.updateUserLastLoginToNow(user.getId());
            log.info("user has successfully logged in: " + user.getUsername());
        }
    }
}
