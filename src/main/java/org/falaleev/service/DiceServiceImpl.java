package org.falaleev.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.falaleev.entity.DiceEntity;
import org.falaleev.mapper.DiceMapper;
import org.falaleev.model.DiceDto;
import org.falaleev.repository.DiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiceServiceImpl implements DiceService {
    private final DiceRepository repository;
    private final DiceMapper mapper;

    @Override
    public List<DiceDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public DiceDto add(String name, String image) {
        DiceEntity dice = new DiceEntity();
        dice.setName(name);
        dice.setImage(image);
        return mapper.toDto(repository.save(dice));
    }

    @Override
    @Transactional
    public DiceDto update(UUID id, String name, String image) {
        DiceEntity dice = repository.findById(id).orElseThrow(() -> new RuntimeException("Dice not found"));
        dice.setName(name);
        dice.setImage(image);
        return mapper.toDto(repository.save(dice));
    }

    @Override
    public DiceDto getById(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new RuntimeException("Dice not found"));
    }
}
