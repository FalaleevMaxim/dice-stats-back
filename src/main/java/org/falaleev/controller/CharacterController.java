package org.falaleev.controller;

import lombok.RequiredArgsConstructor;
import org.falaleev.model.CharacterCreateDto;
import org.falaleev.model.CharacterDto;
import org.falaleev.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/character")
@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/all")
    public List<CharacterDto> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @PostMapping("/add")
    public CharacterDto add(@RequestBody CharacterCreateDto characterCreateDto) {
        return characterService.addCharacter(characterCreateDto.name());
    }

    @PutMapping("/{id}")
    public CharacterDto update(@RequestBody CharacterCreateDto characterCreateDto,
                               @PathVariable("id") UUID id) {
        return characterService.update(id, characterCreateDto.name());
    }
}
