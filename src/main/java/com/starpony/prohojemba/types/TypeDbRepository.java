package com.starpony.prohojemba.types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TypeDbRepository implements TypeRepository{
    private final TypeMapper typeMapper;

    @Autowired
    public TypeDbRepository(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public List<Type> findAll() {
        return typeMapper.findAll();
    }

    @Override
    public void create(Type type) {
        typeMapper.create(type);
        System.out.println(type.getId());
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void delete(Type type) {
        typeMapper.delete(type);
    }
}
