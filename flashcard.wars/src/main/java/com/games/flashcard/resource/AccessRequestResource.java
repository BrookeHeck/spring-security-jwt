package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.NewOrganizationRequestDto;
import com.games.flashcard.service.AccessRequestService;
import com.games.flashcard.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "request-access")
@RequiredArgsConstructor
public class AccessRequestResource {
    private final AccessRequestService accessRequestService;
    private final OrganizationService organizationService;

    @GetMapping(value = "get-new-organization-requests")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION') and hasAuthority('SUPER')")
    public ResponseEntity<List<NewOrganizationRequestDto>> getNewOrganizationRequests() {
        return new ResponseEntity<>(accessRequestService.getAllNewOrganizationRequests(), OK);
    }

    @PostMapping(value = "create-new-access-request")
    public
}
