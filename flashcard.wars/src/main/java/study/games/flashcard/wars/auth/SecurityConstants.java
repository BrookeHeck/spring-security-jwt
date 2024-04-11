package study.games.flashcard.wars.auth;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 3_600_000;
    public static final String TOKEN_HEADER = "Bearer ";
    public static final String JWT_HEADER = "jwt-header";
    public static final String JWT_CANNOT_BE_VERIFIED = "JWT cannot be verified";
    public static final String AUTHORITIES = "Authorities";
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register"};
}
