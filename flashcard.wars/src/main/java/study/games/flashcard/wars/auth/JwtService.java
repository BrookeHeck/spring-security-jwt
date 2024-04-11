package study.games.flashcard.wars.auth;

import org.springframework.beans.factory.annotation.Value;

public class JwtService {
    @Value("${spring.security.secret-key}")
    private String secret;
}
