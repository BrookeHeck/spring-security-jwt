package com.games.flashcard.service;

import com.games.flashcard.model.dtos.NewOrganizationRequestDto;
import com.games.flashcard.model.entities.StudentAccessRequest;

import java.util.List;

public interface AccessRequestService {
    List<NewOrganizationRequestDto> getAllNewOrganizationRequests();
    void deleteOldOrganizationRequests();
    void deleteNewOrganizationRequestById(long orgId);
    List<StudentAccessRequest> getStudentAccessRequestsByOrganizationId();
    void deleteOldStudentAccessRequests();
    void deleteStudentAccessRequestById(long requestId);
}
