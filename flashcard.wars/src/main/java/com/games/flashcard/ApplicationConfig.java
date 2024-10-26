package com.games.flashcard;


import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.model.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.games.flashcard.model.enums.USER_STATUS;
import com.games.flashcard.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AppUser user = userRepo.findAppUserByUsernameOrEmail(username).orElseThrow(() ->
                    new UsernameNotFoundException("user not found: " + username));
            if(user.getStatus() != USER_STATUS.ACTIVE) {
                throw new LockedException("user tried to login with " + user.getStatus() + " status: " + username);
            }
            return new UserPrinciple(user, null);
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
