package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "organization")
@RequiredArgsConstructor
public class OrganizationResource {
    private final OrganizationService orgService;

    @PostMapping(value = "create-organization")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION')")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Validated OrganizationDto organizationDto) {
        return new ResponseEntity<>(orgService.createOrganization(organizationDto), CREATED);
    }

    @DeleteMapping(value = "delete-organization/{orgId}")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION')")
    public ResponseEntity<Boolean> deleteOrganization(@PathVariable long orgId) {
        orgService.deleteOrganizationById(orgId);
        return new ResponseEntity<>(true, OK);
    }

}
