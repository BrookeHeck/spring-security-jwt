package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(long id);

    boolean deleteRoleById(long id);

    boolean deleteRolesByUserId(long userId);

    boolean deleteRolesByOrganizationId(long organizationId);

    List<Role> findRolesByUserId(long userId);

    List<Role> findRolesByOrganizationId(long organizationId);

    List<Role> findRolesByRole(ROLE role);

    List<Role> findRolesByRoleAndOrganizationId(ROLE role, long organizationId);


}
