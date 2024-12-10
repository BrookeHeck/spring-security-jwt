package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.model.query_models.UserDetailsForRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(long id);

    void deleteById(long id);

    boolean deleteRolesByUserId(long userId);

    @Modifying
    @Query("delete Role r where r.organization.id = :ORG_ID")
    void deleteRolesByOrganizationId(@Param("ORG_ID") long organizationId);

    List<Role> findRolesByUserId(long userId);

    List<Role> findRolesByOrganizationId(long organizationId);

    List<Role> findRolesByRole(ROLE role);

    @Query("select r from Role r where r.role=:ROLE and r.organization.id=:ORG_ID")
    List<Role> findRolesByRoleAndOrganizationId(@Param("ROLE") ROLE role, @Param("ORG_ID") long organizationId);

    @Query("select new com.games.flashcard.model.query_models.UserDetailsForRole(r.id, r.user.id, r.user.firstName," +
            "r.user.lastName, r.user.email, r.dateCreated, r.user.status) " +
            "from Role r where r.role=:ROLE and r.organization.id=:ORG_ID  ")
    List<UserDetailsForRole> getUsersForRoleByOrganization(@Param("ORG_ID") long orgId, @Param("ROLE") ROLE role);

}
