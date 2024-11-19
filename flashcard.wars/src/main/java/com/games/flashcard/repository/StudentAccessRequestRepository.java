package com.games.flashcard.repository;

import com.games.flashcard.model.entities.StudentAccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAccessRequestRepository extends JpaRepository<StudentAccessRequest, Long> {
}
