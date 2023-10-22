package com.starpony.prohojemba.titles.repositories;

import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.titles.models.Title;
import com.starpony.prohojemba.titles.repositories.filters.TitlesListFilter;

import java.util.List;
import java.util.Optional;


public interface TitlesRepository {
    public List<Title> getAll(TitlesListFilter filter);

    public Optional<Title> getById(int id, int userId);

    public void create(Title title);

    public void update(Title title);

    public void delete(int id);

    public void updateProgress(int titleId, int userId, TitleProgress titleProgress);
}
