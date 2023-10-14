package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.dto.TypeEditDto;

import com.starpony.prohojemba.types.dto.TypeListViewDto;
import com.starpony.prohojemba.types.dto.TypeViewDto;
import com.starpony.prohojemba.types.exceptions.TypeAlreadyExistException;
import com.starpony.prohojemba.types.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public TypeListViewDto getAllTypes() {
        List<Type> types = typesService.getAll();
        return new TypeListViewDto(types.stream().map(type ->
                new TypeViewDto(type.getId(), type.getViewName())).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public TypeViewDto createType(@RequestBody TypeEditDto typeEditDto) throws TypeAlreadyExistException {
        Type type = typesService.create(typeEditDto);
        return new TypeViewDto(type.getId(), type.getViewName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TypeViewDto updateType(@PathVariable int id, @RequestBody TypeEditDto typeEditDto) throws TypeAlreadyExistException {
        Type type = typesService.update(id, typeEditDto);
        return new TypeViewDto(type.getId(), type.getViewName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteType(@PathVariable int id) {
        typesService.delete(id);
    }
}
