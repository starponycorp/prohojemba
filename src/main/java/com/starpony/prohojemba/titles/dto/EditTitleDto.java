package com.starpony.prohojemba.titles.dto;


import com.starpony.prohojemba.types.dto.TypeDto;

public class EditTitleDto {
    private String name;
    private String cover;
    private TypeDto type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
    }
}
