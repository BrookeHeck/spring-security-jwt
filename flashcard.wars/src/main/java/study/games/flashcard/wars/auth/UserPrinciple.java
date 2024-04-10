package study.games.flashcard.wars.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.games.flashcard.wars.enums.USER_STATUS;
import study.games.flashcard.wars.models.entities.User;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
public class UserPrinciple implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDate yearAgo = LocalDate.now().minusYears(1);
        return user.getLastLoginDate().isAfter(yearAgo);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == USER_STATUS.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LocalDate days120Ago = LocalDate.now().minusDays(120);
        return user.getLastPasswordUpdate().isAfter(days120Ago);
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == USER_STATUS.ACTIVE;
    }
}
