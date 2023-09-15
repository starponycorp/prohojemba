package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.models.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface AccountMapper {
    @Select("select * from accounts where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.starpony.prohojemba.mappers.PermissionMapper.selectByAccount", fetchType = FetchType.LAZY))
    })
    Account selectById(int id);

    @Select("select * from accounts where email = #{email}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.starpony.prohojemba.mappers.PermissionMapper.selectByAccount", fetchType = FetchType.LAZY))
    })
    Account selectByEmail(String email);

    @Select("select * from accounts where accounts.username=#{username}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.starpony.prohojemba.mappers.PermissionMapper.selectByAccount", fetchType = FetchType.LAZY))
    })
    Account selectByUsername(String username);

    @Insert("insert into accounts(email, encodedPassword, username, avatar, isLocked) values (#{email}, #{encodedPassword}, #{username}, #{avatar}, #{isLocked})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Account account);

    @Update("update accounts set email=#{email}, encodedPassword=#{encodedPassword}, username=#{username}, avatar=#{avatar}, isLocked=#{isLocked} where id=#{id}")
    void update(Account account);

    @Delete("delete from accounts where id=#{id}")
    void delete(int id);
}
