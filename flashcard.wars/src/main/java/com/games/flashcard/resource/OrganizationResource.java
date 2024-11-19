package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "organization")
@RequiredArgsConstructor
public class OrganizationResource {
    private final OrganizationService orgService;

    @PostMapping(value = "create-organization")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION')")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Validated OrganizationDto organizationDto) {
        return null;
    }

}
