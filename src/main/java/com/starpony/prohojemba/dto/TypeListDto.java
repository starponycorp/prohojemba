package com.starpony.prohojemba.dto;

import com.starpony.prohojemba.dto.TypeDto;

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
