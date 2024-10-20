package com.games.flashcard.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.games.flashcard.model.enums.USER_STATUS;
import com.games.flashcard.model.entities.AppUser;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class UserPrinciple implements UserDetails {
    private AppUser appUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = appUser.getRoles().stream().flatMap(role ->
                role.getRole().getPermissions().stream().map(permission ->
                        new SimpleGrantedAuthority(permission.toString()))).collect(Collectors.toList());
        List<GrantedAuthority> organizationIds = appUser.getRoles().stream().map(role ->
                        new SimpleGrantedAuthority(Long.toString(role.getOrganization().getId())))
                .collect(Collectors.toList());
        authorities.addAll(organizationIds);
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
