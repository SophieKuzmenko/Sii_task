package com.github.skuzmenko.Sii_task.box.dto;

import java.math.BigDecimal;

public record CollectionBoxDTO(Long id, BigDecimal plnAmount, BigDecimal euroAmount, BigDecimal usdAmount,
                               Long eventId) {
}
