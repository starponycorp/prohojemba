package com.starpony.prohojemba.titles;

import java.util.List;
import java.util.Optional;

public interface TitleRepository {
    public List<Title> findAll(QueryParams queryParams);

    public Optional<Title> find(int id);

    public void create(Title title);

    public void update(Title title);

    public void delete(int id);
}
