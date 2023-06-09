package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.titles.dto.EditTitleDto;
import com.starpony.prohojemba.titles.dto.TitleDto;
import com.starpony.prohojemba.titles.dto.TitleDtoMapper;
import com.starpony.prohojemba.titles.dto.TitleListDto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/titles")
public class TitlesController {
    private final TitleService titleService;

    @Autowired
    public TitlesController(TitleService titleService) {
        this.titleService = titleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public TitleListDto getTitles(@Valid QueryParams queryParams) {
        return TitleDtoMapper.mapToTitleListDto(titleService.getAll(queryParams));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TitleDto getTitle(@PathVariable int id) {
        return TitleDtoMapper.mapToTitleDto(titleService.getOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public TitleDto createTitle(@RequestBody EditTitleDto titleDto) {
        Title title = TitleDtoMapper.mapToTitle(titleDto);
        titleService.create(title);
        return TitleDtoMapper.mapToTitleDto(title);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TitleDto updateTitle(@PathVariable int id, @RequestBody EditTitleDto titleDto) {
        Title title = TitleDtoMapper.mapToTitle(titleDto);
        title.setId(id);
        titleService.update(title);
        return TitleDtoMapper.mapToTitleDto(title);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTitle(@PathVariable int id) {
        titleService.delete(id);
    }
}
