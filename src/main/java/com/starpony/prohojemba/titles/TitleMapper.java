package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.models.Title;
import org.apache.ibatis.annotations.*;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

import java.util.List;


@Mapper
public interface TitleMapper {
    @Select(
            "select titles.id as idTitle, titles.name, titles.cover, types.id as idType, types.viewname from titles left join types on titles.type=types.id " +
            "${whereClauseProvider.whereClause} order by titles.name limit #{limit} offset #{offset}")
    @Results(value =
            {
                    @Result(property = "id", column = "idTitle"),
                    @Result(property = "type.id", column = "idType"),
                    @Result(property = "type.viewName", column = "viewName")
            })
    List<Title> selectAll(
            @Param("whereClauseProvider") WhereClauseProvider whereClause,
            @Param("limit") int limit, @Param("offset") int offset);

    @Select("select titles.id as idTitle, titles.name, titles.cover, types.id as idType, types.viewname from titles left join types on titles.type=types.id where titles.id=#{id}")
    @Results(value =
            {
                    @Result(property = "id", column = "idTitle"),
                    @Result(property = "type.id", column = "idType"),
                    @Result(property = "type.viewName", column = "viewName")
            })
    Title selectById(int id);

    @Insert("insert into titles(name, cover, type) values (#{name}, #{cover}, #{type.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Title title);

    @Update("update titles set name=#{name}, cover=#{cover}, type=#{type.id} where id=#{id}")
    void update(Title title);

    @Delete("delete from titles where id=#{id}")
    void delete(int id);
}
