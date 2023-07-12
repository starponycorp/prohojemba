package com.starpony.prohojemba.users;

import com.starpony.prohojemba.users.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserDbRepository implements UserRepository{
    private final UserMapper userMapper;

    @Autowired
    public UserDbRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findOne(int id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }

    @Override
    public void updateProfile(User user) {
        User userForUniqueCheck = userMapper.selectByUsername(user.getUsername());
        if (userForUniqueCheck != null && userForUniqueCheck.getId() != user.getId())
            throw new UserAlreadyExistsException(
                    String.format("User with username=%s already exists", user.getUsername())
            );

        userMapper.updateProfile(user);
    }
}
