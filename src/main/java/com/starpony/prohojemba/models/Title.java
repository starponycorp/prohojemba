package com.starpony.prohojemba.models;

import com.starpony.prohojemba.models.Type;

import java.util.Objects;


public class Title {
    private int id;
    private String name;
    private String cover;
    private Type type;
    private String progress;

    public Title() {}

    public Title(int id, String name, String cover, Type type) {
        this.id = id;this.name = name;this.cover = cover;this.type = type;
    }

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

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Title title)
            return this.id == title.id &&
                    Objects.equals(this.name, title.name) &&
                    Objects.equals(this.cover, title.cover) &&
                    Objects.equals(this.type, title.type);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cover, type);
    }
}
