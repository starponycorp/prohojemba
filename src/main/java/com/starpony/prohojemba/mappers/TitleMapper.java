package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.models.Title;
import org.apache.ibatis.annotations.*;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

import java.util.List;


@Mapper
public interface TitleMapper {
    @Select(
            "select titles.*, types.viewname, accounttitles.progress from titles " +
                    "left join types on types.id=titles.type " +
                    "left join accounttitles on accounttitles.title = titles.id and accounttitles.account = #{userId} " +
                    "${whereClause} order by titles.name limit #{limit} offset #{offset}")
    @Results(value =
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "type.id", column = "type"),
                    @Result(property = "type.viewName", column = "viewName"),
                    @Result(property = "progress", column = "progress")
            })
    List<Title> selectAllWithUserProgress(
            @Param("whereClause") String whereClause, @Param("userId") int userId,
            @Param("limit") int limit, @Param("offset") int offset);

    @Select(
            "select titles.*, types.viewname, accounttitles.progress from titles " +
                    "left join types on types.id=titles.type " +
                    "left join accounttitles on accounttitles.title = titles.id and accounttitles.account = #{userId} " +
                    "where titles.id = #{id}")
    @Results(value =
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "type.id", column = "type"),
                    @Result(property = "type.viewName", column = "viewName"),
                    @Result(property = "progress", column = "progress")
            })
    Title selectByIdWithUserProgress(int id, int userId);

    @Insert("insert into titles(name, cover, type) values (#{name}, #{cover}, #{type.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Title title);

    @Update("update titles set name=#{name}, cover=#{cover}, type=#{type.id} where id=#{id}")
    void update(Title title);

    @Delete("delete from titles where id=#{id}")
    void delete(int id);

    @Update("insert into accounttitles(account, title, state) values (#{accountId}, #{titleId}, #{state}) " +
            "on conflict(account, title) do update set state=#{state}")
    void createOrUpdateProgress(int titleId, int userId, TitleProgress progress);
}
