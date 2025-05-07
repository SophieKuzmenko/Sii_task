package com.github.skuzmenko.Sii_task.box.dto;

public class CreateBoxDTO {
    private final Long eventId;

    public CreateBoxDTO(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }
}
