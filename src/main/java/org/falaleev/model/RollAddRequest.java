package org.falaleev.model;

import java.util.UUID;

public record RollAddRequest(
        int result,
        UUID character,
        UUID dice
) {
}
