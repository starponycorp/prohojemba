package com.starpony.prohojemba.types.dto;

import java.util.List;


public class TypeListViewDto {
    private List<TypeViewDto> items;

    public TypeListViewDto(List<TypeViewDto> items) {
        this.items = items;
    }

    public List<TypeViewDto> getItems() {
        return items;
    }

    public void setItems(List<TypeViewDto> items) {
        this.items = items;
    }
}
