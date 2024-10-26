package com.games.flashcard.util;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MapperConfiguration {
    Converter<AppUser, UserDto> appUserUserDtoConverter = context -> {
        AppUser entity = context.getSource();
        UserDto dto = context.getDestination();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setProfileImageUrl(entity.getProfileImageUrl());
        dto.setLastLoginDate(entity.getLastLoginDate());
        dto.setDateJoined(entity.getDateJoined());
        Set<RoleDto> roleDtos = entity.getRoles().stream().map(role -> new RoleDto(
                role.getId(), role.getRole(), role.getOrganization().getId(), role.getUser().getId()))
                .collect(Collectors.toSet());
        dto.setRoles(roleDtos);
        return dto;
    };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(appUserUserDtoConverter);
        return modelMapper;
    }
}
