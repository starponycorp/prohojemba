package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.exceptions.TypeNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Validated
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getOne(int id) {
        return typeRepository.findOne(id).orElseThrow(() -> new TypeNotFoundException(
                String.format("Type with id=%s not found", id)));
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
