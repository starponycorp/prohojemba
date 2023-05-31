package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.titles.dto.EditTitleDto;
import com.starpony.prohojemba.titles.dto.TitleDto;
import com.starpony.prohojemba.titles.dto.TitleListDto;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/titles")
public class TitlesController {
    @RequestMapping(method = RequestMethod.GET)
    public TitleListDto getTitles(QueryParams queryParams) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TitleDto getTitle(@PathVariable int id) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public TitleDto createTitle(@PathVariable int id, @RequestBody EditTitleDto titleDto) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TitleDto updateTitle(@PathVariable int id, @RequestBody EditTitleDto titleDto) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTitle(@PathVariable int id) {
        // Удаление тайтла
    }
}
