package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.converters.TypeConverter;
import com.starpony.prohojemba.dto.TypeEditDto;
import com.starpony.prohojemba.services.TypesService;
import com.starpony.prohojemba.dto.TypeDto;
import com.starpony.prohojemba.dto.TypeListDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/types")
public class TypesController {
    private final TypesService typesService;

    @Autowired
    public TypesController(TypesService typesService) {
        this.typesService = typesService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public TypeListDto getAllTypes() {
        TypeListDto typeListDto = new TypeListDto();
        typeListDto.setItems(typesService.getAll().stream().map(TypeConverter::mapTo).collect(Collectors.toList()));

        return typeListDto;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TypeDto createType(@RequestBody TypeEditDto typeEditDto) {
        return TypeConverter.mapTo(typesService.create(typeEditDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TypeDto updateType(@PathVariable int id, @RequestBody TypeEditDto typeEditDto) {
        return TypeConverter.mapTo(typesService.update(id, typeEditDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable int id) {
        typesService.delete(id);
    }
}
