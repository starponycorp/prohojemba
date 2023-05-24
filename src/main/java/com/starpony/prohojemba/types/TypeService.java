package com.starpony.prohojemba.types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public void create(Type type) {
        typeRepository.create(type);
    }

    public void update(Type type) {
        typeRepository.update(type);
    }

    public void delete(Type type) {
        typeRepository.delete(type);
    }
}
