package com.starpony.prohojemba.users;

import com.starpony.prohojemba.models.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Select("select * from accounts left join profiles on profiles.account = accounts.id where accounts.id=#{id}")
    User selectById(int id);

    @Select("select * from accounts left join profiles on profiles.account = accounts.id where profiles.username=#{username}")
    User selectByUsername(String username);

    @Insert("insert into accounts(email, encodedPassword, isLocked) values (#{email}, #{encodedPassword}, #{isLocked})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createAccount(User user);

    @Insert("insert into profiles(account, username, avatar) values (#{id}, #{username}, #{avatar})")
    void createProfile(User user);

    @Update("update accounts set email=#{email}, encodedPassword=#{encodedPassword}, isLocked=#{isLocked} where id=#{id}")
    void updateAccount(User user);

    @Update("update profiles set username=#{username}, avatar=#{avatar} where account=#{id}")
    void updateProfile(User user);

    @Delete("delete from accounts where id=#{id}")
    void delete(int id);
}
