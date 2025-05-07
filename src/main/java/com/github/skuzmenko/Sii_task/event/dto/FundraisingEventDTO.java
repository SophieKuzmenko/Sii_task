package com.github.skuzmenko.Sii_task.event.dto;

import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;

import java.util.List;

public class FundraisingEventDTO {
    private Long id;
    private String name;
    private Double account;
    private String currency;
    private List<CollectionBoxDTO> boxes;

    public FundraisingEventDTO(Long id, String name, Double account, String currency, List<CollectionBoxDTO> boxes) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.currency = currency;
        this.boxes = boxes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAccount() {
        return account;
    }

    public String getCurrency() {
        return currency;
    }

    public List<CollectionBoxDTO> getBoxes() {
        return boxes;
    }
}
