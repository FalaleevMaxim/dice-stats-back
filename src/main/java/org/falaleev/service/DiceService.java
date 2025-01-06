package org.falaleev.service;

import org.falaleev.model.DiceDto;

import java.util.List;
import java.util.UUID;

public interface DiceService {
    List<DiceDto> getAll();

    DiceDto add(String name, String image);

    DiceDto update(UUID id, String name, String image);

    DiceDto getById(UUID id);
}
