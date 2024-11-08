package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(long id);

    boolean deleteById(long id);

    Role findRoleByUserId(long userId);

    Role findRoleByOrganizationId(long organizationId);

    Role findRoleByRole(ROLE role);

    Role findRoleByRoleAndOrganizationId(ROLE role, long organizationId);
}
