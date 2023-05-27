package com.starpony.prohojemba.types;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TypeMapper {
    @Select("select * from types")
    List<Type> findAll();

    @Insert("insert into types(viewName) values (#{viewName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Type type);

    @Update("update types set viewName=#{viewName} where id=#{id}")
    void update(Type type);

    @Delete("delete from types where id=#{id}")
    void delete(int id);
}
