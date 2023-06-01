package com.starpony.prohojemba.titles;

import java.util.List;

public interface TitleRepository {
    public List<Title> findAll(QueryParams queryParams);

    public Title find(int id);

    public void create(Title title);

    public void update(Title title);

    public void delete(Title title);
}
