package com.starpony.prohojemba.titles.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TitleEditDto {
    @NotBlank
    private String name;
    @Pattern(regexp = "/media/covers/\\w+.webp", message = "Cover must have a link /media/covers/...")
    private String cover;
    @Min(value = 0, message = "Type id cannot be less than 0")
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
