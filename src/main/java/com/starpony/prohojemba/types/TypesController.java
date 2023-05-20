package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.dto.TypeDto;
import com.starpony.prohojemba.types.dto.TypeListDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
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
    public void createType(@RequestBody TypeDto typeDto) {
        typeService.create(new Type());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateType(@PathVariable int id, @RequestBody TypeDto typeDto) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable int id) {
        
    }
}
