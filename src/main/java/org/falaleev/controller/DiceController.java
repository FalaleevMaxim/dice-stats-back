package org.falaleev.controller;

import lombok.RequiredArgsConstructor;
import org.falaleev.model.DiceDto;
import org.falaleev.service.DiceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public DiceDto add(@RequestParam("image") MultipartFile file,
                       @RequestParam("name") String name) throws IOException {
        return service.add(name, file.getBytes());
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public DiceDto update(@RequestParam("image") MultipartFile file,
                          @RequestParam("name") String name,
                          @PathVariable("id") UUID id) throws IOException {
        return service.update(id, name, file.getBytes());
    }
}
