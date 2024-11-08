package com.games.flashcard.repository;

import com.games.flashcard.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(long id);

    boolean deleteById(long id);

    Role findRoleByUserId(long userId);

    Role findRoleByOrganizationId(long organizationId);
}
