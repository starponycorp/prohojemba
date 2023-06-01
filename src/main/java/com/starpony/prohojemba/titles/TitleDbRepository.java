package com.starpony.prohojemba.titles;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TitleDbRepository implements TitleRepository{
    @Override
    public List<Title> findAll(QueryParams queryParams) {
        return null;
    }

    @Override
    public Optional<Title> find(int id) {
        return null;
    }

    @Override
    public void create(Title title) {

    }

    @Override
    public void update(Title title) {

    }

    @Override
    public void delete(int id) {

    }
}
