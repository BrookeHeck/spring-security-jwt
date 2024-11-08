package com.games.flashcard.auth.filter;

import com.games.flashcard.auth.SecurityConstants;
import com.games.flashcard.auth.UserPrinciple;
import com.games.flashcard.auth.service.JwtService;
import com.games.flashcard.model.entities.Role;
import com.games.flashcard.model.enums.PERMISSION;
import com.games.flashcard.model.enums.ROLE;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
        String username = jwtService.getSubject(token);
        boolean tokenIsValid = jwtService.isTokenValid(username, token);
        if(tokenIsValid && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<GrantedAuthority> authorities = jwtService.getAuthorities(token);
            Authentication authentication = jwtService.getAuthentication(username, authorities, request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
