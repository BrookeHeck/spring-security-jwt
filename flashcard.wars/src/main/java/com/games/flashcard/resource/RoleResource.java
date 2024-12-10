package com.games.flashcard.resource;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.enums.ROLE;
import com.games.flashcard.model.query_models.UserDetailsForRole;
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
    @GetMapping(value = "get-all-role-users-organization/{orgId}/{role}")
//    @PreAuthorize("@authorization.userCanEditRoleAtOrg(#root, #orgId, #role)")
    public ResponseEntity<List<RoleDto>> getAllRoleUsersForOrganization(@PathVariable long orgId,
                                                                        @PathVariable ROLE role) {
        return new ResponseEntity<>(roleService.findRolesByRoleAndOrganizationId(role, orgId), OK);
    }

    @GetMapping(value = "get-user-details-for-role-by-org/{orgId}/{role}")
    public ResponseEntity<List<UserDetailsForRole>> getUsersForRoleByOrganization(@PathVariable long orgId,
                                                                                         @PathVariable ROLE role) {
        return new ResponseEntity<>(roleService.getUsersForRoleByOrganization(orgId, role), OK);
    }

    @PostMapping(value = "create-role")
    @PreAuthorize("@authorization.userCanEditRoleAtOrg(#root, #roleDto.organizationId, #roleDto.role)")
    public ResponseEntity<RoleDto> createRole(@RequestBody  @Validated RoleDto roleDto) {
        return new ResponseEntity<>(roleService.addNewRole(roleDto), CREATED);
    }

    @DeleteMapping(value = "delete-role")
    @PreAuthorize("@authorization.userCanEditRoleAtOrg(#root, #roleDto.organizationId, #roleDto.role)")
    public ResponseEntity<Boolean> deleteRole(@RequestBody @Validated RoleDto roleDto) {
        roleService.deleteRoleById(roleDto.getId());
        return new ResponseEntity<>(true, OK);
    }


}
