package com.games.flashcard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreAuthorizeService {
    public boolean userHasPermissionsToDeleteRole() {
        return false;
    }

    public boolean userHasPermissionToCreateRole() {
        return false;
    }

    public boolean userHasPermissionToViewRole() {
        return false;
    }
}
