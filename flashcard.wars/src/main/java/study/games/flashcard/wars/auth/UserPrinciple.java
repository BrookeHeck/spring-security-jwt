package study.games.flashcard.wars.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.games.flashcard.wars.models.enums.USER_STATUS;
import study.games.flashcard.wars.models.entities.AppUser;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@Getter
public class UserPrinciple implements UserDetails {
    private AppUser appUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        LocalDate yearAgo = LocalDate.now().minusYears(1);
        return appUser.getLastLoginDate().isAfter(yearAgo);
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.getStatus() == USER_STATUS.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LocalDate days120Ago = LocalDate.now().minusDays(120);
        return appUser.getLastPasswordUpdate().isAfter(days120Ago);
    }

    @Override
    public boolean isEnabled() {
        return appUser.getStatus() == USER_STATUS.ACTIVE;
    }
}
