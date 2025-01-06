package org.falaleev.controller;

import lombok.RequiredArgsConstructor;
import org.falaleev.model.DiceCreateRequest;
import org.falaleev.model.DiceDto;
import org.falaleev.service.DiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/dice")
@RestController
@RequiredArgsConstructor
public class DiceController {
    private final DiceService service;

    @GetMapping("/all")
    public List<DiceDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DiceDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping(value = "/add")
    public DiceDto add(@RequestBody DiceCreateRequest diceCreateRequest) {
        return service.add(diceCreateRequest.name(), diceCreateRequest.image());
    }

    @PutMapping(value = "/{id}")
    public DiceDto update(@RequestBody DiceCreateRequest diceCreateRequest,
                          @PathVariable("id") UUID id) {
        return service.update(id, diceCreateRequest.name(), diceCreateRequest.image());
    }
}
