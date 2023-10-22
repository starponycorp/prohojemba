package com.starpony.prohojemba.titles.repositories.filters;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


public class TitlesListFilter {
    @Min(value = 0, message = "Type id cannot be less than 0")
    private int type;

    private String nameSearch;

    @Min(value = 5, message = "Limit min value is 5")
    @Max(value = 100, message = "Limit max value is 100")
    private int limit;

    @Min(value = 0, message = "Offset cannot be less than 0")
    private int offset;

    public TitlesListFilter(int type, String nameSearch, int limit, int offset) {
        this.type = type;
        this.nameSearch = nameSearch;
        this.limit = limit;
        this.offset = offset;
    }

    public int getType() {
        return type;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
