package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.filters.TitlesFilter;
import com.starpony.prohojemba.models.Title;

import java.util.List;
import java.util.Optional;

public interface TitlesRepository {
    public List<Title> getAll(TitlesFilter titlesFilter, int limit, int offset, int userId);

    public Optional<Title> getById(int id, int userId);

    public void create(Title title);

    public void update(Title title);

    public void delete(int id);

    public void updateProgress(int titleId, int userId, TitleProgress titleProgress);
}
