package com.games.flashcard.auth;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.dtos.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.games.flashcard.model.enums.USER_STATUS;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record UserPrinciple(UserDto appUser) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = appUser.getAuthorities().stream().map(authority ->
                new SimpleGrantedAuthority(authority.toString())).collect(Collectors.toSet());
        RoleDto selectedRole = appUser.getSelectedRole();
        if(selectedRole != null) {
            authorities.add(new SimpleGrantedAuthority(selectedRole.getRole().toString()));
            authorities.add(new SimpleGrantedAuthority(Long.toString(selectedRole.getOrganizationId())));
        }
        return authorities;
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
