package com.starpony.prohojemba.titles;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class QueryParams {
    @Min(value = 5, message = "limit min value is 5")
    @Max(value = 100, message = "limit max value is 100")
    private int limit = 10;

    @Min(value = 0, message = "offset cannot be less than 0")
    private int offset = 0;

    @Min(value = 0, message = "type id cannot be less than 0")
    private int type; // Фильтрация по типу тайтла

    private String search; // Поиск по названию тайтла

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
