package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationResource extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(long id);

    @Query("select o from Organization o")
    List<Organization> findAllOrganizations();

    Organization findOrganizationByOrganizationCode(String organizationCode);

    @Modifying
    @Query("update Organization o set o.displayName=:DISPLAY_NAME where o.id = :ORG_ID")
    int updateOrganizationDisplayNameById(@Param("DISPLAY_NAME") String displayName, @Param("ORG_ID") long orgId);
}
