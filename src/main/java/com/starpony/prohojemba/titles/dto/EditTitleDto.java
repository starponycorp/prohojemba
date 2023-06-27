package com.starpony.prohojemba.titles.dto;


import com.starpony.prohojemba.types.dto.TypeDto;

public class EditTitleDto {
    private String name;
    private String cover;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
