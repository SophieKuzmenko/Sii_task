package com.github.skuzmenko.Sii_task.box.dto;

public class BoxInfoDTO {
    private final Boolean isAssigned;
    private final Boolean isEmpty;

    public BoxInfoDTO(Boolean isAssigned, Boolean isEmpty) {
        this.isAssigned = isAssigned;
        this.isEmpty = isEmpty;
    }

    public Boolean getAssigned() {
        return isAssigned;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }
}
