package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.exceptions.TypeAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TypeDbRepository implements TypeRepository{
    private final TypeMapper typeMapper;

    @Autowired
    public TypeDbRepository(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public List<Type> findAll() {
        return typeMapper.selectAll();
    }

    @Override
    public Optional<Type> findOne(int id) {
        return Optional.ofNullable(typeMapper.selectById(id));
    }

    @Override
    public void create(Type type) {
        // Проверка уникальности viewName
        if (typeMapper.selectByViewName(type.getViewName()) != null)
            throw new TypeAlreadyExistsException(
                    String.format("Type with viewName=%s already exists", type.getViewName()));

        typeMapper.create(type);
    }

    @Override
    public void update(Type type) {
        // Проверка уникальности viewName
        Type typeForUniqueCheck = typeMapper.selectByViewName(type.getViewName());
        if (typeForUniqueCheck != null && typeForUniqueCheck.getId() != type.getId())
            throw new TypeAlreadyExistsException(
                    String.format("Type with viewName=%s already exists", type.getViewName()));

        typeMapper.update(type);
    }

    @Override
    public void delete(int id) {
        typeMapper.delete(id);
    }
}
