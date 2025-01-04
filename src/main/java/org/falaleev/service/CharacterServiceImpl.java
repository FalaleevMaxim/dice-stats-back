package org.falaleev.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.falaleev.entity.CharacterEntity;
import org.falaleev.mapper.CharacterMapper;
import org.falaleev.model.CharacterDto;
import org.falaleev.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Override
    public List<CharacterDto> getAllCharacters() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public CharacterDto addCharacter(String name) {
        CharacterEntity character = new CharacterEntity();
        character.setName(name);
        return mapper.toDto(repository.save(character));
    }

    @Override
    @Transactional
    public CharacterDto update(UUID id, String name) {
        CharacterEntity character = repository.findById(id).orElseThrow(() -> new RuntimeException("Character not found"));
        character.setName(name);
        return mapper.toDto(repository.save(character));
    }
}
