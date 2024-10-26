package com.games.flashcard.auth;

import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.games.flashcard.model.enums.USER_STATUS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class UserPrinciple implements UserDetails {
    private UserDto appUser;
    private Role selectedRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getAuthorities().stream().map(authority ->
                new SimpleGrantedAuthority(authority.toString())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDateTime yearAgo = LocalDateTime.now().minusYears(1);
        return appUser.getLastLoginDate().isAfter(yearAgo);
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.getStatus() == USER_STATUS.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LocalDateTime days120Ago = LocalDateTime.now().minusDays(120);
        return appUser.getLastPasswordUpdate().isAfter(days120Ago);
    }

    @Override
    public boolean isEnabled() {
        return appUser.getStatus() == USER_STATUS.ACTIVE;
    }

}
