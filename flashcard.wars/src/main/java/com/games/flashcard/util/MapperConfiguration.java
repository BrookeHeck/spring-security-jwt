package com.games.flashcard.util;

import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.entities.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(flashcardListToFlashcardIds);
        modelMapper.addConverter(listRoleToRoleDto);
        modelMapper.addConverter(roleDtoRoleConverter);
        return modelMapper;
    }

    Converter<Set<Role>, Set<RoleDto>> listRoleToRoleDto = new AbstractConverter<>() {
        @Override
        protected Set<RoleDto> convert(Set<Role> source) {
            return source.stream().map(role -> new RoleDto(
                    role.getId(), role.getRole(), role.getOrganization().getId(),
                    role.getOrganization().getDisplayName(), role.getUser().getId())).collect(Collectors.toSet());
        }
    };

    Converter<List<Flashcard>, List<Long>> flashcardListToFlashcardIds = new AbstractConverter<>() {
        @Override
        protected List<Long> convert(List<Flashcard> source) {
            return source.stream().map(Flashcard::getId).collect(Collectors.toList());
        }
    };

    Converter<RoleDto, Role> roleDtoRoleConverter = new AbstractConverter<>() {
        @Override
        protected Role convert(RoleDto source) {
            Organization organization = new Organization();
            organization.setId(source.getOrganizationId());
            AppUser appUser = new AppUser();
            appUser.setId(source.getId());
            return new Role(source.getId(), source.getRole(), appUser, organization);
        }
    }

}
