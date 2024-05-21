package com.games.flashcard.listener;

import com.games.flashcard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import com.games.flashcard.auth.service.LoginAttemptService;
import com.games.flashcard.model.enums.USER_STATUS;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFailureListener {
    private final LoginAttemptService loginAttemptService;
    private final UserService userService;

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent badCredentialsEvent) throws ExecutionException {
        Object principal = badCredentialsEvent.getAuthentication().getPrincipal();
        if(principal instanceof String username) {
            try {
                loginAttemptService.addUserToLoginAttemptCache(username);
                log.warn("user attempted to login with invalid credentials: " + username);
            } catch (LockedException e) {
                userService.changeAccountStatus(USER_STATUS.PASSWORD_LOCK, username);
                log.warn("user's account has been locked: " + username);
                throw new LockedException("account has exceeded login attempts: " + username);
            }
        }
    }
}
