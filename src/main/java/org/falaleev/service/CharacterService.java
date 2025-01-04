package org.falaleev.service;

import org.falaleev.model.CharacterDto;

import java.util.List;
import java.util.UUID;

public interface CharacterService {
    List<CharacterDto> getAll();

    CharacterDto addCharacter(String name);

    CharacterDto update(UUID id, String name);

    CharacterDto getById(UUID id);
}
