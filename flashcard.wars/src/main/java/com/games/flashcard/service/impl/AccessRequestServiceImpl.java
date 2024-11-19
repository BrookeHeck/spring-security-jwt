package com.games.flashcard.service.impl;

import com.games.flashcard.model.dtos.NewOrganizationRequestDto;
import com.games.flashcard.model.dtos.StudentAccessRequestDto;
import com.games.flashcard.repository.NewOrganizationRequestRepository;
import com.games.flashcard.repository.StudentAccessRequestRepository;
import com.games.flashcard.service.AccessRequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessRequestServiceImpl implements AccessRequestService {
    private final ModelMapper modelMapper;
    private final StudentAccessRequestRepository studentAccessRequestRepo;
    private final NewOrganizationRequestRepository newOrganizationRequestRepo;

    @Override
    public List<NewOrganizationRequestDto> getAllNewOrganizationRequests() {
        return newOrganizationRequestRepo.getAllOrganizationRequests().stream()
                .map(request -> modelMapper.map(request, NewOrganizationRequestDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteOldOrganizationRequests() {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        newOrganizationRequestRepo.deleteOldOrganizationRequests(threeMonthsAgo);
    }

    @Override
    public void deleteNewOrganizationRequestById(long requestId) {
        newOrganizationRequestRepo.deleteNewOrganizationRequestById(requestId);
    }

    @Override
    public List<StudentAccessRequestDto> getStudentAccessRequestsByOrganizationId(long orgId) {
        return studentAccessRequestRepo.findStudentAccessRequestByOrganizationId(orgId).stream()
                .map(request -> modelMapper.map(request, StudentAccessRequestDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteStudentAccessRequestById(long requestId) {
        studentAccessRequestRepo.deleteStudentAccessRequestById(requestId);
    }
}
