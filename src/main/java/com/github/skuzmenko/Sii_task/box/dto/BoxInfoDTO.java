package com.github.skuzmenko.Sii_task.box.dto;

public class BoxInfoDTO {
    private final Long id;
    private final Boolean isAssigned;
    private final Boolean isEmpty;

    public BoxInfoDTO(Long id, Boolean isAssigned, Boolean isEmpty) {
        this.id = id;
        this.isAssigned = isAssigned;
        this.isEmpty = isEmpty;
    }

    public Boolean getAssigned() {
        return isAssigned;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public Long getId() {
        return id;
    }
}
