package com.starpony.prohojemba.services;

import com.starpony.prohojemba.dto.TypeEditDto;
import com.starpony.prohojemba.exceptions.ItemNotFoundException;
import com.starpony.prohojemba.models.Type;
import com.starpony.prohojemba.repositories.TypesDatabaseRepository;
import jakarta.validation.Valid;
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

    public Type getOne(int id) {
        return typeRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Type with id=%s not found", id)));
    }

    public Type create(@Valid TypeEditDto typeEditDto) {
        Type type = new Type(0, typeEditDto.getViewName());

        typeRepository.create(type);

        return type;
    }

    public Type update(int id, @Valid TypeEditDto typeEditDto) {
        Type type = new Type(id, typeEditDto.getViewName());

        typeRepository.update(type);

        return type;
    }

    public void delete(int id) {
        typeRepository.delete(id);
    }
}
