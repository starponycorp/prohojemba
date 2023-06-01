package com.starpony.prohojemba.titles.dto;

import com.starpony.prohojemba.titles.Title;
import com.starpony.prohojemba.types.Type;
import com.starpony.prohojemba.types.dto.TypeDtoMapper;

import java.util.List;
import java.util.stream.Collectors;


public class TitleDtoMapper {
    public static Title mapToTitle(EditTitleDto titleDto) {
        Title title = new Title();
        title.setName(titleDto.getName());
        title.setCover(titleDto.getCover());

        Type type = new Type();
        type.setId(titleDto.getType());
        title.setType(type);

        return title;
    }

    public static TitleDto mapToTitleDto(Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(titleDto.getId());
        titleDto.setName(title.getName());
        titleDto.setCover(title.getCover());
        titleDto.setType(TypeDtoMapper.mapToTypeDto(title.getType()));
        titleDto.setProgress(title.getProgress());

        return titleDto;
    }

    public static TitleListDto mapToTitleListDto(List<Title> titles) {
        TitleListDto titleListDto = new TitleListDto();
        titleListDto.setItems(titles.stream().map(
                TitleDtoMapper::mapToTitleDto).collect(Collectors.toList()));

        return titleListDto;
    }
}
