package study.games.flashcard.wars.listeners;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import study.games.flashcard.wars.auth.services.LoginAttemptService;
import study.games.flashcard.wars.models.enums.USER_STATUS;
import study.games.flashcard.wars.service.UserService;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {
    private final LoginAttemptService loginAttemptService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureListener.class);

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent badCredentialsEvent) throws ExecutionException {
        Object principal = badCredentialsEvent.getAuthentication().getPrincipal();
        if(principal instanceof String username) {
            try {
                loginAttemptService.addUserToLoginAttemptCache(username);
                logger.warn("user attempted to login with invalid credentials: " + username);
            } catch (LockedException e) {
                userService.changeAccountStatus(USER_STATUS.PASSWORD_LOCK, username);
                logger.warn("user's account has been locked: " + username);
                throw new LockedException("account has exceeded login attempts: " + username);
            }
        }
    }
}
