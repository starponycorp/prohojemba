package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.models.Type;
import com.starpony.prohojemba.types.TypeService;
import com.starpony.prohojemba.types.dto.TypeDto;
import com.starpony.prohojemba.types.dto.TypeDtoMapper;
import com.starpony.prohojemba.types.dto.TypeListDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/types")
public class TypesController {
    private final TypeService typeService;

    @Autowired
    public TypesController(TypeService typeService) {
        this.typeService = typeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public TypeListDto getAllTypes() {
        return TypeDtoMapper.mapToTypeListDto(typeService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public TypeDto createType(@RequestBody TypeDto typeDto) {
        Type type = TypeDtoMapper.mapToType(typeDto);
        typeService.create(type);
        return TypeDtoMapper.mapToTypeDto(type);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TypeDto updateType(@PathVariable int id, @RequestBody TypeDto typeDto) {
        Type type = TypeDtoMapper.mapToType(typeDto);
        type.setId(id);
        typeService.update(type);
        return TypeDtoMapper.mapToTypeDto(type);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable int id) {
        typeService.delete(id);
    }
}
