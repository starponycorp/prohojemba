package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.exceptions.TypeAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        try {
            typeMapper.create(type);
        } catch (DuplicateKeyException ex) {
            exists(type);
        }
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void delete(int id) {
        typeMapper.delete(id);
    }

    private void exists(Type type) {
        throw new TypeAlreadyExistsException(String.format("Type with viewName=%s already exists", type.getViewName()));
    }
}
