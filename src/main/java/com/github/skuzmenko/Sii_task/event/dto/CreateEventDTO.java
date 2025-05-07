package com.github.skuzmenko.Sii_task.event.dto;

public class CreateEventDTO {
    private final String name;
    private final String currency;

    public CreateEventDTO(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }
}
