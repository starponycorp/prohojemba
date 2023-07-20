package com.starpony.prohojemba.titles;


public interface TitleStateRepository {
    void createOrUpdate(int accountId, int titleId, TitleStateForUser state);
}
