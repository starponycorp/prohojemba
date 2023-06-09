package com.starpony.prohojemba.permissions;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PermissionMapper {
    @Select("select * from permissions")
    List<Permission> selectAll();

    @Select("select * from permissions where id=#{id}")
    Permission selectById(int id);

    @Select("select * from permissions where systemName=#{systemName} or viewName=#{viewName}")
    Permission selectBySystemNameOrViewName(String systemName, String viewName);

    @Insert("insert into permissions(systemName, viewName) values (#{systemName}, #{viewName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Permission permission);

    @Update("update permissions set systemName=#{systemName}, viewName=#{viewName} where id=#{id}")
    void update(Permission permission);

    @Delete("delete from permissions where id=#{id}")
    void delete(int id);
}
