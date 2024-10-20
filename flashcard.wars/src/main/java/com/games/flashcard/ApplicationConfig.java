package com.games.flashcard;


import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.enums.PERMISSION;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.games.flashcard.model.enums.USER_STATUS;
import com.games.flashcard.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

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
            List<PERMISSION> permissions = user.getRoles().stream().flatMap(role ->
                    role.getRole().getPermissions().stream()).collect(Collectors.toList());
            user.setAuthorities(permissions);
            return new UserPrinciple(user);
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

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
