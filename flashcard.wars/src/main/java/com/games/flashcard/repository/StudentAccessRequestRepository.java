package com.games.flashcard.repository;

import com.games.flashcard.model.entities.StudentAccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAccessRequestRepository extends JpaRepository<StudentAccessRequest, Long> {
    List<StudentAccessRequest> findStudentAccessRequestByOrganizationId(long orgId);

    void deleteStudentAccessRequestById(long requestId);
}
