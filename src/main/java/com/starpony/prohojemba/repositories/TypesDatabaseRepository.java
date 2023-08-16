package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.exceptions.ItemAlreadyExistsException;
import com.starpony.prohojemba.mappers.TypeMapper;
import com.starpony.prohojemba.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class TypesDatabaseRepository {
    private final TypeMapper typeMapper;

    @Autowired
    public TypesDatabaseRepository(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    public List<Type> getAll() {
        return typeMapper.selectAll();
    }

    public Optional<Type> getById(int id) {
        return Optional.ofNullable(typeMapper.selectById(id));
    }

    private Optional<Type> getByViewName(String viewName) {
        return Optional.ofNullable(typeMapper.selectByViewName(viewName));
    }

    public void create(Type type) {
        getByViewName(type.getViewName()).ifPresent(typeForUniqueCheck -> {
            throw new ItemAlreadyExistsException(
                    String.format("Type with viewName=%s already exists", type.getViewName())
            );
        });

        typeMapper.create(type);
    }

    public void update(Type type) {
        getByViewName(type.getViewName()).ifPresent(typeForUniqueCheck -> {
            if (typeForUniqueCheck.getId() != type.getId())
                throw new ItemAlreadyExistsException(
                        String.format("Type with viewName=%s already exists", type.getViewName())
                );
        });

        typeMapper.update(type);
    }

    public void delete(int id) {
        typeMapper.delete(id);
    }
}
