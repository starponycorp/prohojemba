package com.starpony.prohojemba.types;

import java.util.List;
import java.util.Optional;


public interface TypeRepository {
    List<Type> findAll();

    Optional<Type> findOne(int id);

    Optional<Type> findOne(String viewName);

    void create(Type type);

    void update(Type type);

    void delete(int id);
}