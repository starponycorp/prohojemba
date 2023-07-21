package com.starpony.prohojemba.titles;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface TitleStateMapper {
    @Update("insert into account_title_states(account, title, state) values (#{accountId}, #{titleId}, #{state}) " +
            "on conflict(account, title) do update set state=#{state}")
    void createOrUpdate(int accountId, int titleId, TitleStateForUser state);
}
