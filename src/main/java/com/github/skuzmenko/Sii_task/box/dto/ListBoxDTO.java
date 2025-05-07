package com.github.skuzmenko.Sii_task.box.dto;

import java.util.List;

public class ListBoxDTO {
    private final List<BoxInfoDTO> list;

    public ListBoxDTO(List<BoxInfoDTO> list) {
        this.list = list;
    }

    public List<BoxInfoDTO> getList() {
        return list;
    }
}
