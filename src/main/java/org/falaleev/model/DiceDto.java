package org.falaleev.model;

import java.util.UUID;

public record DiceDto(
        UUID id,
        String name,
        String image
) {
}
