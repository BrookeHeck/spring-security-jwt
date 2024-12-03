package com.games.flashcard.service.impl;

import com.games.flashcard.model.dtos.NewOrganizationRequestDto;
import com.games.flashcard.model.dtos.OrganizationDto;
import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.dtos.StudentAccessRequestDto;
import com.games.flashcard.model.entities.NewOrganizationRequest;
import com.games.flashcard.model.entities.StudentAccessRequest;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.repository.NewOrganizationRequestRepository;
import com.games.flashcard.repository.StudentAccessRequestRepository;
import com.games.flashcard.service.AccessRequestService;
import com.games.flashcard.service.OrganizationService;
import com.games.flashcard.service.RoleService;
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
    private final OrganizationService orgService;
    private final RoleService roleService;

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
    public NewOrganizationRequestDto createNewOrganizationRequest(NewOrganizationRequestDto requestDto) {
        NewOrganizationRequest request = modelMapper.map(requestDto, NewOrganizationRequest.class);
        return modelMapper.map(newOrganizationRequestRepo.save(request), NewOrganizationRequestDto.class);
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

    @Override
    public StudentAccessRequestDto createStudentAccessRequest(StudentAccessRequestDto requestDto) {
        StudentAccessRequest request = modelMapper.map(requestDto, StudentAccessRequest.class);
        return modelMapper.map(studentAccessRequestRepo.save(request), StudentAccessRequestDto.class);
    }

    @Override
    public double getCountOfNewOrgRequests() {
        return newOrganizationRequestRepo.getCountOfNewOrganizationRequests();
    }

    @Override
    public long acceptNewOrganizationRequest(long requestId, String organizationName, long adminUserId) {
        OrganizationDto organization = orgService.createOrganization(organizationName);
        RoleDto roleDto = new RoleDto(ROLE.ADMIN, organization.getId(), organizationName, adminUserId);
        roleService.addNewRole(roleDto);
        deleteNewOrganizationRequestById(requestId);
        return requestId;
    }
}
