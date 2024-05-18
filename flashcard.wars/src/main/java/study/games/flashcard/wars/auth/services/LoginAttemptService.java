package study.games.flashcard.wars.auth.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;
import study.games.flashcard.wars.exception.domain.AccountNotActiveException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    private static final int MAX_NUMBER_LOGIN_ATTEMPTS = 5;
    private static final int ATTEMPT_INCREMENT = 1;
    private final LoadingCache<String, Integer> loginAttemptCache;

    public LoginAttemptService() {
        super();
        loginAttemptCache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    public void evictUserFromLoginAttemptCache(String usernameOrEmail) {
        loginAttemptCache.invalidate(usernameOrEmail);
    }

    public void addUserToLoginAttemptCache(String usernameOrEmail) throws ExecutionException, AccountNotActiveException {
        int attempts = ATTEMPT_INCREMENT + loginAttemptCache.get(usernameOrEmail);
        if(hasExceededMaxAttempts(attempts)) {
            throw new AccountNotActiveException("account with username or email" + usernameOrEmail + " has exceeded login attempts.");
        } else {
            loginAttemptCache.put(usernameOrEmail, attempts);
        }
    }

    private boolean hasExceededMaxAttempts(int attempts) {
        return attempts >= MAX_NUMBER_LOGIN_ATTEMPTS;
    }
}
