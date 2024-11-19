package com.games.flashcard.service;

import com.games.flashcard.model.dtos.NewOrganizationRequestDto;
import com.games.flashcard.model.dtos.StudentAccessRequestDto;

import java.util.List;

public interface AccessRequestService {
    List<NewOrganizationRequestDto> getAllNewOrganizationRequests();
    void deleteOldOrganizationRequests();
    void deleteNewOrganizationRequestById(long requestId);
    NewOrganizationRequestDto createNewOrganizationRequest(NewOrganizationRequestDto requestDto);
    List<StudentAccessRequestDto> getStudentAccessRequestsByOrganizationId(long orgId);
    void deleteStudentAccessRequestById(long requestId);
    StudentAccessRequestDto createStudentAccessRequest(StudentAccessRequestDto requestDto);
}
