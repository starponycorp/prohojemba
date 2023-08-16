package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.models.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface AccountMapper {
    @Select("select * from accounts where accounts.id = #{id}")
    User selectById(int id);

    @Select("select * from accounts where accounts.username=#{username}")
    User selectByUsername(String username);

    @Insert("insert into accounts(email, encodedPassword, username, avatar, isLocked) values (#{email}, #{encodedPassword}, #{username}, #{avatar}, #{isLocked})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(User user);

    @Update("update accounts set email=#{email}, encodedPassword=#{encodedPassword}, username=#{username}, avatar=#{avatar}, isLocked=#{isLocked} where id=#{id}")
    void update(User user);

    @Delete("delete from accounts where id=#{id}")
    void delete(int id);
}
