package com.starpony.prohojemba.titles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TitleStateDbRepository implements TitleStateRepository{
    private final TitleStateMapper titleStateMapper;

    @Autowired
    public TitleStateDbRepository(TitleStateMapper titleStateMapper) {
        this.titleStateMapper = titleStateMapper;
    }

    @Override
    public void createOrUpdate(int accountId, int titleId, TitleStateForUser state) {
        titleStateMapper.createOrUpdate(accountId, titleId, state);
    }
}
