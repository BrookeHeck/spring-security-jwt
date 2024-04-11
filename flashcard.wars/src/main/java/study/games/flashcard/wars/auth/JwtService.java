package study.games.flashcard.wars.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static study.games.flashcard.wars.auth.SecurityConstants.*;

public class JwtService {
    @Value("${spring.security.secret-key}")
    private String secret;

    public String generateJwt(UserPrinciple userPrinciple) {
        List<String> claims = getClaimsFromUserPrinciple(userPrinciple);
        Date now = new Date();
        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withAudience(JWT_AUDIENCE)
                .withIssuedAt(now)
                .withSubject(userPrinciple.getUsername())
                .withArrayClaim(AUTHORITIES, claims.toArray(new String[0]))
                .withExpiresAt(new Date(now.getTime() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    private List<String> getClaimsFromUserPrinciple(UserPrinciple userPrinciple) {
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : userPrinciple.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities;
    }

}
