package com.starpony.prohojemba.types;

import com.starpony.prohojemba.models.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TypeMapper{
    @Select("select * from types")
    List<Type> selectAll();

    @Select("select * from types where id=#{id}")
    Type selectById(int id);

    @Select("select * from types where viewName=#{viewName}")
    Type selectByViewName(String viewName);

    @Insert("insert into types(viewName) values (#{viewName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Type type);

    @Update("update types set viewName=#{viewName} where id=#{id}")
    void update(Type type);

    @Delete("delete from types where id=#{id}")
    void delete(int id);
}
