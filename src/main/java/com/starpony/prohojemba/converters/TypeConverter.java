package com.starpony.prohojemba.converters;

import com.starpony.prohojemba.dto.TypeDto;
import com.starpony.prohojemba.models.Type;


public class TypeConverter {
    public static TypeDto mapTo(Type type) {
        TypeDto typeDto = new TypeDto();
        typeDto.setId(type.getId());
        typeDto.setViewName(type.getViewName());

        return typeDto;
    }
}
