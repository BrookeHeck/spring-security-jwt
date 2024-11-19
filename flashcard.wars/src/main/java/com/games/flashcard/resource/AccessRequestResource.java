package com.games.flashcard.resource;

import com.games.flashcard.service.AccessRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "request-access")
@RequiredArgsConstructor
public class AccessRequestResource {
    private final AccessRequestService accessRequestService;
}
