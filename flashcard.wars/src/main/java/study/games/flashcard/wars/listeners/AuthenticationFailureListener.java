package study.games.flashcard.wars.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import study.games.flashcard.wars.auth.services.LoginAttemptService;
import study.games.flashcard.wars.exception.domain.AccountNotActiveException;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {
    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent badCredentialsEvent) throws ExecutionException {
        Object principal = badCredentialsEvent.getAuthentication().getPrincipal();
        if(principal instanceof String username) {
            try {
                loginAttemptService.addUserToLoginAttemptCache(username);
            } catch (AccountNotActiveException e) {
                // TODO: set account to inactive, inform user
            }
        }
    }
}
