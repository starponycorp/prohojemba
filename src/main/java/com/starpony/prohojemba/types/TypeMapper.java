package com.starpony.prohojemba.types;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TypeMapper {
    @Select("select * from types")
    List<Type> findAll();

    @Insert("insert into types(viewName) values (#{viewName})")
    void create(String viewName);

    @Update("update types set viewName=#{viewName} where id=#{id}")
    void update(int id, String viewName);

    @Delete("delete from types where id=#{id}")
    void delete(int id);
}
