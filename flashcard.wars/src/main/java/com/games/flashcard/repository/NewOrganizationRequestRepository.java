package com.games.flashcard.repository;

import com.games.flashcard.model.entities.NewOrganizationRequest;
import com.games.flashcard.model.entities.StudentAccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NewOrganizationRequestRepository extends JpaRepository<NewOrganizationRequest, Long> {
    @Query("select request from NewOrganizationRequest request")
    List<NewOrganizationRequest> getAllOrganizationRequests();

    @Modifying
    @Query("delete NewOrganizationRequest request where request.timeOfInsert > :THREE_MONTHS_AGO")
    void deleteOldOrganizationRequests(LocalDateTime threeMonthsAgo);

    void deleteNewOrganizationRequestById(long orgId);

}
