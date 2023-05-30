package com.starpony.prohojemba.types.dto;

import com.starpony.prohojemba.types.Type;

import java.util.List;
import java.util.stream.Collectors;


public class TypeDtoMapper {
    public static Type mapToType(TypeDto typeDto) {
        Type type = new Type();
        type.setId(typeDto.getId());
        type.setViewName(typeDto.getViewName());
        return type;
    }

    public static TypeDto mapToTypeDto(Type type) {
        TypeDto typeDto = new TypeDto();
        typeDto.setId(type.getId());
        typeDto.setViewName(type.getViewName());
        return typeDto;
    }

    public static TypeListDto mapToTypeListDto(List<Type> types) {
        TypeListDto typeListDto = new TypeListDto();
        typeListDto.setItems(types.stream().map(TypeDtoMapper::mapToTypeDto).collect(Collectors.toList()));
        return typeListDto;
    }
}
