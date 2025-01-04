package org.falaleev.mapper;

import org.falaleev.entity.CharacterEntity;
import org.falaleev.model.CharacterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterDto toDto(CharacterEntity character);
}
