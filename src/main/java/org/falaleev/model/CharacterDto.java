package org.falaleev.model;

import java.util.UUID;

public record CharacterDto (
        UUID id,
        String name
) {
}
