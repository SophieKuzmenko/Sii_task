package com.github.skuzmenko.Sii_task.event.dto;

import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;

import java.math.BigDecimal;
import java.util.List;

public record FundraisingEventDTO(Long id, String name, BigDecimal account, String currency,
                                  List<CollectionBoxDTO> boxes) {
}
