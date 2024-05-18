package study.games.flashcard.wars.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import study.games.flashcard.wars.auth.services.LoginAttemptService;
import study.games.flashcard.wars.exception.domain.AccountNotActiveException;
import study.games.flashcard.wars.models.enums.USER_STATUS;
import study.games.flashcard.wars.service.UserService;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {
    private final LoginAttemptService loginAttemptService;
    private final UserService userService;

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent badCredentialsEvent) throws ExecutionException {
        Object principal = badCredentialsEvent.getAuthentication().getPrincipal();
        if(principal instanceof String username) {
            try {
                loginAttemptService.addUserToLoginAttemptCache(username);
            } catch (AccountNotActiveException e) {
                userService.changeAccountStatus(USER_STATUS.PASSWORD_LOCK, username);
                throw new AccountExpiredException("account with username or email " + username + " has exceeded login attempts");
            }
        }
    }
}
