package com.starpony.prohojemba.types.repositories;

import com.starpony.prohojemba.types.repositories.mappers.TypeMapper;
import com.starpony.prohojemba.types.exceptions.TypeAlreadyExistException;
import com.starpony.prohojemba.types.models.Type;
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

    public void create(Type type) throws TypeAlreadyExistException{
        Optional<Type> alreadyExistedType = getByViewName(type.getViewName());
        if (alreadyExistedType.isPresent())
            throw new TypeAlreadyExistException(
                    String.format("Type with viewName=%s already exists", type.getViewName())
            );

        typeMapper.create(type);
    }

    public void update(Type type) throws TypeAlreadyExistException{
        Optional<Type> alreadyExistedType = getByViewName(type.getViewName());
        if (alreadyExistedType.isPresent() && alreadyExistedType.get().getId() == type.getId())
            throw new TypeAlreadyExistException(
                    String.format("Type with viewName=%s already exists", type.getViewName())
            );

        typeMapper.update(type);
    }

    public void delete(int id) {
        typeMapper.delete(id);
    }
}
