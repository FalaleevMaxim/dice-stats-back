package org.falaleev.mapper;

import org.falaleev.entity.DiceEntity;
import org.falaleev.model.DiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiceMapper {
    DiceDto toDto(DiceEntity dice);
}
