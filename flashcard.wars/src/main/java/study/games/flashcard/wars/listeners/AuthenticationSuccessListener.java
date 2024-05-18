package study.games.flashcard.wars.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import study.games.flashcard.wars.auth.services.LoginAttemptService;
import study.games.flashcard.wars.models.entities.AppUser;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessListener {
    private final LoginAttemptService loginAttemptService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Object principle = authenticationSuccessEvent.getAuthentication().getPrincipal();
        if(principle instanceof AppUser user) {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
            loginAttemptService.evictUserFromLoginAttemptCache(user.getEmail());
        }
    }
}
