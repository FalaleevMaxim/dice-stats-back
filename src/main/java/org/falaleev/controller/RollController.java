package org.falaleev.controller;

import lombok.RequiredArgsConstructor;
import org.falaleev.model.RollAddRequest;
import org.falaleev.model.RollHistoryItem;
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
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "from", required = false) LocalDateTime from,
            @RequestParam(name = "to", required = false) LocalDateTime to,
            @RequestParam(name = "diceId", required = false) UUID diceId,
            @RequestParam(name = "characterId", required = false) UUID characterId
            ) {
        return rollService.getHistory(limit, from, to, diceId, characterId);
    }

    @PostMapping("/add")
    public UUID add(@RequestBody RollAddRequest rollAddRequest) {
        return rollService.add(rollAddRequest);
    }
}
