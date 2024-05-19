package study.games.flashcard.wars.exception.domain;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.games.flashcard.wars.model.dtos.Response;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Response<String>> accountDisabledException() {
        log.error("Attempted login of disabled user");
        return createHttpResponse(HttpStatus.UNAUTHORIZED,
                "Your account is disabled. If this is a mistake please create a help ticket.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response<String>> badCredentialsException() {
        log.error("Attempted login with bad credentials");
        return createHttpResponse(HttpStatus.FORBIDDEN, "Incorrect username and/or password.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<String>> accessDeniedException() {
        log.error("User attempted to access unauthorized content");
        return createHttpResponse(HttpStatus.FORBIDDEN, "You do not have access to this content.");
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Response<String>> lockedException() {
        log.error("User with locked account attempted to login");
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "Your account is locked. Please reset password to continue.");
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Response<String>> tokenExpiredException() {
        log.error("User with expired token attempted to access");
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "Your session has expired. Please login to continue.");
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Response<String>> emailExistsException() {
        log.warn("User attempted to register with email that is already in use");
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "An account already exists with this email.");
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<Response<String>> usernameExistsException() {
        log.warn("User attempted to register with username that already exists");
        return createHttpResponse(HttpStatus.UNAUTHORIZED, "An account already exists with this username.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> internalServerError(Exception e) {
        log.error("Internal Server Error", e);
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "There was error processing your request.");
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Response<String>> noResultException(NoResultException e) {
        log.error("No Result Exception", e);
        return createHttpResponse(HttpStatus.NOT_FOUND, "There was error processing your request.");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Response<String>> ioException(IOException e) {
        log.error("IO Exception", e);
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "There was error processing your request.");
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
