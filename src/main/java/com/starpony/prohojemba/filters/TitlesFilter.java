package com.starpony.prohojemba.filters;

import jakarta.validation.constraints.Min;


public class TitlesFilter implements Filter{
    @Min(value = 0, message = "Type id cannot be less than 0")
    private int type;
    private String nameSearch;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = "%" + nameSearch + "%";
    }

    @Override
    public String toWhereClause() {
        String whereClause = null;

        if (type > 0)
            whereClause = String.format("where titles.type == %s", type);

        if (nameSearch != null) {
            whereClause = whereClause == null?String.format("where titles.name ilike '%s'", nameSearch):
                    String.format("%s and titles.name ilike '%s'", whereClause, nameSearch);
        }

        return whereClause == null?"":whereClause;
    }
}
