package com.games.flashcard.model.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class Response<T> {
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
    private String message;
    private T data;
}
