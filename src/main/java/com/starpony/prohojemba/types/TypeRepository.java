package com.starpony.prohojemba.types;

import java.util.List;
import java.util.Optional;


public interface TypeRepository {
    List<Type> findAll();

    void create(Type type);

    void update(Type type);

    void delete(Type type);
}