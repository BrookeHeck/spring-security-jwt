package com.games.flashcard.repository;

import com.games.flashcard.model.entities.NewOrganizationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewOrganizationRequestRepository extends JpaRepository<NewOrganizationRequest, Long> {
}
