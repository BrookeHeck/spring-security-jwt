package study.games.flashcard.wars.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<GrantedAuthority> getAuthorities(String jwt) {
        List<String> claims = getClaimsFromJwt(jwt);
        return claims.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private List<String> getClaimsFromJwt(String jwt) {
        JWTVerifier verifier = getJwtVerifier();
        return verifier.verify(jwt).getClaim(AUTHORITIES).asList(String.class);
    }

    private JWTVerifier getJwtVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(JWT_ISSUER).build();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw new JWTVerificationException("token cannot be verified");
        }
        return verifier;
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken userPassAuthToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return userPassAuthToken;
    }

    private boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJwtVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }


    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

}
