package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "role")
@RequiredArgsConstructor
@Slf4j
public class RoleResource {
    private final RoleService roleService;

    @PostMapping(value = "create-organization-admin")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION_ADMINS')")
    public ResponseEntity<RoleDto> createOrganizationAdmin(@RequestBody  @Validated RoleDto roleDto) {
        return new ResponseEntity<>(roleService.addNewRole(roleDto), CREATED);
    }

    @GetMapping(value = "get-all-organization-admins")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION_ADMINS')")
    public ResponseEntity<List<RoleDto>> getAllOrganizationAdmins() {
        return new ResponseEntity<>(roleService.findRolesByRole(ROLE.ADMIN), OK);
    }

    @DeleteMapping("delete-admin-role/{roleId}")
    @PreAuthorize("hasAuthority('MANAGE_ORGANIZATION_ADMINS')")
    public ResponseEntity<Boolean> deleteOrganizationAdminByRoleId(@PathVariable long roleId) {
        return new ResponseEntity<>(roleService.deleteRoleById(roleId), OK);
    }

}
