package study.games.flashcard.wars.models.dtos;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
public class Response<T> {
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private Date timeStamp;
    private String message;
    private T data;
}
