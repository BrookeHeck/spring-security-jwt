package study.games.flashcard.wars.exception.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.games.flashcard.wars.models.dtos.Response;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

    private Response<String> createHttpResponse(HttpStatus status, String message) {
        return Response.<String>builder()
                .timeStamp(LocalDateTime.now())
                .httpStatusCode(status.value())
                .httpStatus(status)
                .message(status.getReasonPhrase())
                .data(message)
                .build();
    }
}
