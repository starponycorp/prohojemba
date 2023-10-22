package com.starpony.prohojemba.permissions.reposiitories.mappers;

import com.starpony.prohojemba.permissions.models.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PermissionMapper {
    @Select("select * from permissions")
    List<Permission> selectAll();

    @Select("select p.* from accountpermissions ap join permissions p ON p.id = ap.permission where ap.account = #{accountId}")
    List<Permission> selectByAccount(int accountId);

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
