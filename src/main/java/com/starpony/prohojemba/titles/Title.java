package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.types.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class Title {
    private int id;
    private String name;
    private String cover;
    private Type type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
