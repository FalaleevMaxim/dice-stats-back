package org.falaleev.service;

import org.falaleev.model.DiceDto;

import java.util.List;
import java.util.UUID;

public interface DiceService {
    List<DiceDto> getAll();

    DiceDto add(String name, byte[] image);

    DiceDto update(UUID id, String name, byte[] image);
}
