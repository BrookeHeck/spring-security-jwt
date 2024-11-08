package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationResource extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(long id);

    Organization findOrganizationByOrganizationCode(String organizationCode);
}
