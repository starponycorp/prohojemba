package com.starpony.prohojemba.users;

import com.starpony.prohojemba.models.User;

import java.util.Optional;


public interface UserRepository {
    Optional<User> findOne(int id);

    //void create(User user);

    void updateProfile(User user);
}
