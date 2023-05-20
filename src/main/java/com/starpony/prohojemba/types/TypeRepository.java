package com.starpony.prohojemba.types;

import java.util.List;
import java.util.Optional;


public interface TypeRepository {
    List<Type> findAll();

    Optional<Type> getById(int id);

    Type create(Type type);

    Type update(Type type);

    void delete(int id);
}
