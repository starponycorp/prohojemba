package com.starpony.prohojemba.users;

import java.util.Optional;


public interface UserRepository {
    Optional<User> findOne(int id);

    //void create(User user);

    void updateProfile(User user);
}
