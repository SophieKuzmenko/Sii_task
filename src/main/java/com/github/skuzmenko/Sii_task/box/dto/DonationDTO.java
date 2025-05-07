package com.github.skuzmenko.Sii_task.box.dto;

public class DonationDTO {
    private final Double amount;
    private final String currency;

    public DonationDTO(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
