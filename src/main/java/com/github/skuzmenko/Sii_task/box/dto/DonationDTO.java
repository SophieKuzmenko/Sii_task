package com.github.skuzmenko.Sii_task.box.dto;

import java.math.BigDecimal;

public record DonationDTO(BigDecimal amount, String currency) {
}
