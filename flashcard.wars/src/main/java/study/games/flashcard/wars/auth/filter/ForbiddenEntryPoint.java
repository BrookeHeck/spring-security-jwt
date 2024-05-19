package study.games.flashcard.wars.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import study.games.flashcard.wars.model.dtos.Response;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class ForbiddenEntryPoint extends Http403ForbiddenEntryPoint {
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException arg2) throws IOException {
        Response<String> httpResponse = Response.<String>builder()
                .httpStatus(FORBIDDEN)
                .httpStatusCode(FORBIDDEN.value())
                .timeStamp(LocalDateTime.now())
                .message(FORBIDDEN.getReasonPhrase().toUpperCase())
                .data("You need to login to access this page.")
                .build();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }
}
