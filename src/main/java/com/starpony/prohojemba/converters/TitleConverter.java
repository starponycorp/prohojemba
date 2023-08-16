package com.starpony.prohojemba.converters;

import com.starpony.prohojemba.dto.TitleDto;
import com.starpony.prohojemba.models.Title;


public class TitleConverter {
    public static TitleDto mapTo(Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(title.getId());
        titleDto.setName(title.getName());
        titleDto.setCover(title.getCover());
        titleDto.setProgress(title.getProgress());
        titleDto.setType(TypeConverter.mapTo(title.getType()));

        return titleDto;
    }
}
