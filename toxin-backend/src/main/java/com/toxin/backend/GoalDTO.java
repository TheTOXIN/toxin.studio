package com.toxin.backend;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link Goal}
 */
public record GoalDTO(
        UUID id,
        String title,
        BigDecimal value,
        BigDecimal donate,
        LocalDateTime created
) {

}