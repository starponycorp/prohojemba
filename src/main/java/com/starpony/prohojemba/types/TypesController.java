package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.dto.TypeDto;
import com.starpony.prohojemba.types.dto.TypeListDto;

import jakarta.validation.Valid;
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

        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TypeDto createType(@Valid @RequestBody TypeDto typeDto) {
        Type type = typeDto.toType();
        typeService.create(type);
        return new TypeDto(type);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateType(@PathVariable int id, @RequestBody TypeDto typeDto) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable int id) {
        
    }
}
