package com.games.flashcard.util;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
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
    Converter<AppUser, UserDto> appUserUserDtoConverter = new AbstractConverter<>() {
        @Override
        protected UserDto convert(AppUser source) {
            UserDto dto = new UserDto();
            dto.setId(source.getId());
            dto.setUserId(source.getUserId());
            dto.setUsername(source.getUsername());
            dto.setEmail(source.getEmail());
            dto.setFirstName(source.getFirstName());
            dto.setLastName(source.getLastName());
            dto.setProfileImageUrl(source.getProfileImageUrl());
            dto.setLastLoginDate(source.getLastLoginDate());
            dto.setDateJoined(source.getDateJoined());
            Set<RoleDto> roleDtos = source.getRoles().stream().map(role -> new RoleDto(
                            role.getId(), role.getRole(), role.getOrganization().getId(), source.getId()))
                    .collect(Collectors.toSet());
            dto.setRoles(roleDtos);
            return dto;
        }
    };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(appUserUserDtoConverter);
        return modelMapper;
    }
}
