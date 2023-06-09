package com.starpony.prohojemba.titles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TitleDbRepository implements TitleRepository{
    private final TitleMapper titleMapper;

    @Autowired
    public TitleDbRepository(TitleMapper titleMapper) {
        this.titleMapper = titleMapper;
    }

    @Override
    public List<Title> findAll(QueryParams queryParams) {
        return titleMapper.selectAll(queryParams);
    }

    @Override
    public Optional<Title> find(int id) {
        return Optional.ofNullable(titleMapper.selectById(id));
    }

    @Override
    public void create(Title title) {
        titleMapper.create(title);
    }

    @Override
    public void update(Title title) {
        titleMapper.update(title);
    }

    @Override
    public void delete(int id) {
        titleMapper.delete(id);
    }
}
