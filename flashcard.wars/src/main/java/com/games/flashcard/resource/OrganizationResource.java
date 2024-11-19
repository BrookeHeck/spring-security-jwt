package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "organization")
@RequiredArgsConstructor
public class OrganizationResource {
    private final OrganizationService orgService;

    @PostMapping(value = "create-organization")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION') and hasAuthority('SUPER')")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Validated OrganizationDto organizationDto) {
        return new ResponseEntity<>(orgService.createOrganization(organizationDto), CREATED);
    }

    @DeleteMapping(value = "delete-organization/{orgId}")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION') and hasAuthority('SUPER')")
    public ResponseEntity<Boolean> deleteOrganization(@PathVariable long orgId) {
        orgService.deleteOrganizationById(orgId);
        return new ResponseEntity<>(true, OK);
    }

    @GetMapping(value = "get-all-organizations")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION') and hasAuthority('SUPER')")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        return new ResponseEntity<>(orgService.findAllOrganizations(), OK);
    }

    @PutMapping(value = "update-org-name/{orgId}")
    @PreAuthorize("hasAuthority('EDIT_ORGANIZATION_DETAILS') and hasAuthority(#orgId)")
    public ResponseEntity<Boolean> updateOrganizationDisplayName(@PathVariable long orgId, @RequestBody String displayName) {
        return new ResponseEntity<>(orgService.updateOrganizationDisplayName(displayName, orgId), OK);
    }

}
