package com.starpony.prohojemba.dto;

import com.starpony.prohojemba.dto.TitleDto;

import java.util.List;

public class TitleListDto {
    private List<TitleDto> items;

    public List<TitleDto> getItems() {
        return items;
    }

    public void setItems(List<TitleDto> items) {
        this.items = items;
    }
}
