package org.falaleev.model;

import java.time.LocalDateTime;

public record RollHistoryItem(
        int result,
        LocalDateTime date
) {

}