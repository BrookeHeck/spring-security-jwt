package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Organization;
import com.games.flashcard.model.query_models.OrganizationOverviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(long id);

    @Query("select o from Organization o")
    List<Organization> findAllOrganizations();

    Organization findOrganizationByOrganizationCode(String organizationCode);

    @Modifying
    @Query("update Organization o set o.displayName=:DISPLAY_NAME where o.id = :ORG_ID")
    int updateOrganizationDisplayNameById(@Param("DISPLAY_NAME") String displayName, @Param("ORG_ID") long orgId);

    @Query("select o.id, o.displayName, sum(case when r.role = 'ADMIN' then 1 else 0 end),  " +
            "sum(case when r.role = 'TEACHER' then 1 else 0 end), " +
            "sum(case when r.role = 'TEACHER_ASSISTANT' then 1 else 0 end), " +
            "sum(case when r.role = 'STUDENT' then 1 else 0 end)" +
            "from Role r join Organization  o on r.organization = o where o.id = :ORG_ID")
    List<OrganizationOverviewDetails> getOrganizationOverviewDetails();
}
