package com.starpony.prohojemba.types;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Validated
@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public void create(@Valid Type type) {
        typeRepository.create(type);
    }

    public void update(@Valid Type type) {
        typeRepository.update(type);
    }

    public void delete(int id) {
        typeRepository.delete(id);
    }
}
