package org.falaleev.controller;

import lombok.RequiredArgsConstructor;
import org.falaleev.model.CharacterCreateDto;
import org.falaleev.model.CharacterDto;
import org.falaleev.model.RollAddRequest;
import org.falaleev.model.RollHistoryItem;
import org.falaleev.service.CharacterService;
import org.falaleev.service.RollService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping("/roll")
@RestController
@RequiredArgsConstructor
public class RollController {
    private  final RollService rollService;

    @GetMapping("/history")
    public List<RollHistoryItem> getHistory(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) LocalDateTime from,
            @RequestParam(required = false) LocalDateTime to,
            @RequestParam(required = false) UUID diceId,
            @RequestParam(required = false) UUID characterId
            ) {
        return rollService.getHistory(limit, from, to, diceId, characterId);
    }

    @PostMapping("/add")
    public UUID add(@RequestBody RollAddRequest rollAddRequest) {
        return rollService.add(rollAddRequest);
    }
}
