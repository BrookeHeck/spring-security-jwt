package com.games.flashcard.util;

import com.games.flashcard.model.dtos.FlashcardDto;
import com.games.flashcard.model.dtos.FlashcardSetDto;
import com.games.flashcard.model.dtos.RoleDto;
import com.games.flashcard.model.dtos.UserDto;
import com.games.flashcard.model.entities.AppUser;
import com.games.flashcard.model.entities.Flashcard;
import com.games.flashcard.model.entities.FlashcardSet;
import com.games.flashcard.model.entities.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
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

}
