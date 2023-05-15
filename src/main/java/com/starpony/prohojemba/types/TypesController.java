package com.starpony.prohojemba.types;

import com.starpony.prohojemba.types.dto.TypeDto;
import com.starpony.prohojemba.types.dto.TypeListDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/types")
public class TypesController {
    @RequestMapping(method = RequestMethod.GET)
    public TypeListDto getAllTypes() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createType(@RequestBody TypeDto typeDto) {

    }

    @RequestMapping(value = "/{systemName}", method = RequestMethod.PUT)
    public void updateType(@PathVariable String systemName, @RequestBody TypeDto typeDto) {

    }

    @RequestMapping(value = "/{systemName}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable String systemName) {

    }
}
