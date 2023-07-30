package com.starpony.prohojemba.types.dto;

import java.util.List;


public class TypeListDto {
    private List<TypeDto> items;

    public void setItems(List<TypeDto> types) {
        items = types;
    }

    public List<TypeDto> getItems() {
        return items;
    }
}
