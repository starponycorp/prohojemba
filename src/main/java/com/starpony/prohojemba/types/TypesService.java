package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.dto.TypeEditDto;
import com.starpony.prohojemba.types.exceptions.TypeAlreadyExistException;
import com.starpony.prohojemba.types.exceptions.TypeNotFoundException;
import com.starpony.prohojemba.types.models.Type;
import com.starpony.prohojemba.types.repositories.TypesDatabaseRepository;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Validated
public class TypesService {
    private final TypesDatabaseRepository typeRepository;

    @Autowired
    public TypesService(TypesDatabaseRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAll() {
        return typeRepository.getAll();
    }

    public Type getOne(int id) throws TypeNotFoundException {
        return typeRepository.getById(id).orElseThrow(() ->
                new TypeNotFoundException(String.format("Type with id=%s not found", id)));
    }

    public Type create(@Valid TypeEditDto typeEditDto) throws ValidationException, TypeAlreadyExistException {
        Type type = new Type(0, typeEditDto.getViewName());
        typeRepository.create(type);
        return type;
    }

    public Type update(int id, @Valid TypeEditDto typeEditDto) throws ValidationException, TypeAlreadyExistException {
        Type type = new Type(id, typeEditDto.getViewName());
        typeRepository.update(type);
        return type;
    }

    public void delete(int id) {
        typeRepository.delete(id);
    }
}
