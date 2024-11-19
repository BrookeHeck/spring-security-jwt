package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.ROLE;
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

}
