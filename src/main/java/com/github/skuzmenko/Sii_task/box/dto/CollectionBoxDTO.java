package com.github.skuzmenko.Sii_task.box.dto;

public class CollectionBoxDTO {
    private final Long id;
    private final Double plnAmount;
    private final Double euroAmount;
    private final Double usdAmount;
    private final Long eventId;

    public CollectionBoxDTO(Long id, Double plnAmount, Double euroAmount, Double usdAmount, Long eventId) {
        this.id = id;
        this.plnAmount = plnAmount;
        this.euroAmount = euroAmount;
        this.usdAmount = usdAmount;
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public Double getPlnAmount() {
        return plnAmount;
    }

    public Double getEuroAmount() {
        return euroAmount;
    }

    public Double getUsdAmount() {
        return usdAmount;
    }

    public Long getEventId() {
        return eventId;
    }
}
