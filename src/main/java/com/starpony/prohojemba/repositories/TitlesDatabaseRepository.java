package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.filters.TitlesFilter;
import com.starpony.prohojemba.mappers.TitleMapper;
import com.starpony.prohojemba.models.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TitlesDatabaseRepository implements TitlesRepository{
    private final TitleMapper titleMapper;

    @Autowired
    public TitlesDatabaseRepository(TitleMapper titleMapper) {
        this.titleMapper = titleMapper;
    }

    @Override
    public List<Title> getAll(TitlesFilter titlesFilter, int limit, int offset, int userId) {
        return titleMapper.selectAllWithUserProgress(titlesFilter.toWhereClause(), userId, limit, offset);
    }

    @Override
    public Optional<Title> getById(int id, int userId) {
        return Optional.ofNullable(titleMapper.selectByIdWithUserProgress(id, userId));
    }

    @Override
    public void create(Title title) {
        titleMapper.create(title);
    }

    @Override
    public void update(Title title) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void updateProgress(int titleId, int userId, TitleProgress titleProgress) {

    }
}
