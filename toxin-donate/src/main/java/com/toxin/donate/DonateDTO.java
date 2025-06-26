package com.toxin.donate;

import java.math.BigDecimal;

public record DonateDTO (
        Long id,
        String message,
        BigDecimal donate
) {
}
