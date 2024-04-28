package study.games.flashcard.wars.exception.domain;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.games.flashcard.wars.models.dtos.Response;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Response<String>> accountDisabledException() {
        logger.error("Attempted login of disabled user");
        return createHttpResponse(HttpStatus.UNAUTHORIZED,
                "Your account is disabled. If this is a mistake please create a help ticket.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response<String>> badCredentialsException() {
        logger.error("Attempted login with bad credentials");
        return createHttpResponse(HttpStatus.FORBIDDEN, "Incorrect username and/or password.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<String>> accessDeniedException() {
        logger.error("User attempted to access unauthorized content");
        return createHttpResponse(HttpStatus.FORBIDDEN, "You do not have access to this content");
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Response<String>> lockedException() {
        logger.error("User with locked account attempted to login");
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "Your account is locked. Please reset password to continue.");
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Response<String>> tokenExpiredException() {
        logger.error("User with expired token attempted to access");
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "Your token is expired. Please login to continue.");
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Response<String>> emailExistsException() {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "An account already exists with this email");
    }

    

    private ResponseEntity<Response<String>> createHttpResponse(HttpStatus status, String message) {
        Response<String> response = Response.<String>builder()
                .timeStamp(LocalDateTime.now())
                .httpStatusCode(status.value())
                .httpStatus(status)
                .message(status.getReasonPhrase())
                .data(message)
                .build();
        return new ResponseEntity<>(response, status);
    }
}
