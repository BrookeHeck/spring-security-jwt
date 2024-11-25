package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.NewOrganizationRequestDto;
import com.games.flashcard.model.dtos.StudentAccessRequestDto;
import com.games.flashcard.service.AccessRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "request-access")
@RequiredArgsConstructor
public class AccessRequestResource {
    private final AccessRequestService accessRequestService;

    @GetMapping(value = "get-new-organization-requests")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION') and hasAuthority('SUPER')")
    public ResponseEntity<List<NewOrganizationRequestDto>> getNewOrganizationRequests() {
        return new ResponseEntity<>(accessRequestService.getAllNewOrganizationRequests(), OK);
    }

    @PostMapping(value = "create-new-organization-request")
    public ResponseEntity<NewOrganizationRequestDto> createNewOrganizationRequest(
            @RequestBody @Validated NewOrganizationRequestDto request) {
        return new ResponseEntity<>(accessRequestService.createNewOrganizationRequest(request), CREATED);
    }

    @DeleteMapping(value = "deny-new-organization-request/{requestId}")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION' and hasAuthority('SUPER'))")
    public ResponseEntity<Boolean> denyNewOrganizationRequest(@PathVariable long requestId) {
        accessRequestService.deleteNewOrganizationRequestById(requestId);
        return new ResponseEntity<>(true, OK);
    }

    @GetMapping(value = "get-student-access-requests/{orgId}")
    @PreAuthorize("hasAuthority('ACCEPT_STUDENT_ACCESS_REQUEST') and hasAuthority(#orgId)")
    public ResponseEntity<List<StudentAccessRequestDto>> getStudentAccessRequests(@PathVariable long orgId) {
        return new ResponseEntity<>(accessRequestService.getStudentAccessRequestsByOrganizationId(orgId), OK);
    }

    @PostMapping(value = "create-student-access-request")
    public ResponseEntity<StudentAccessRequestDto> createStudentAccessRequest(@RequestBody @Validated StudentAccessRequestDto request) {
        return new ResponseEntity<>(accessRequestService.createStudentAccessRequest(request), CREATED);
    }

    @DeleteMapping(value = "deny-student-access-request/{requestId}/{orgId}")
    @PreAuthorize("hasAuthority('ACCEPT_STUDENT_ACCESS_REQUEST') and hasAuthority(#orgId)")
    public ResponseEntity<Boolean> denyStudentAccessRequest(@PathVariable long requestId, @PathVariable long orgId) {
        accessRequestService.deleteStudentAccessRequestById(requestId);
        return new ResponseEntity<>(true, OK);
    }
}
