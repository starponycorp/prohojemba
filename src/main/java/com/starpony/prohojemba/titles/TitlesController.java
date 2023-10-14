package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.converters.TitleConverter;
import com.starpony.prohojemba.dto.TitleEditDto;
import com.starpony.prohojemba.dto.TitleDto;
import com.starpony.prohojemba.dto.TitleListDto;
import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.filters.TitlesFilter;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.titles.TitlesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/titles")
public class TitlesController {
    private final TitlesService titlesService;

    @Autowired
    public TitlesController(TitlesService titlesService) {
        this.titlesService = titlesService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public TitleListDto getTitles(
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam int type,
            @RequestParam String search
    ) {
        TitlesFilter titlesFilter = new TitlesFilter();
        titlesFilter.setType(type);
        titlesFilter.setNameSearch(search);

        Account account = new Account();
        account.setId(1);

        TitleListDto titleListDto = new TitleListDto();
        titleListDto.setItems(titlesService.getAll(titlesFilter, limit, offset, account.getId())
                .stream().map(TitleConverter::mapTo).collect(Collectors.toList()));

        return titleListDto;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TitleDto getTitle(@PathVariable int id) {
        Account account = new Account();
        account.setId(1);

        return TitleConverter.mapTo(titlesService.getOne(id, account.getId()));
    }

    @RequestMapping(value = "/{titleId}/progress", method = RequestMethod.POST)
    public void updateTitleProgressForUser(@PathVariable int titleId, @RequestParam(name = "value") String progress) {
        TitleProgress titleProgress = TitleProgress.valueOf(progress.toUpperCase());

        Account account = new Account();
        account.setId(1);

        titlesService.updateProgressForUser(account.getId(), titleId, titleProgress);
    }

    @RequestMapping(method = RequestMethod.POST)
    public TitleDto createTitle(@RequestBody TitleEditDto titleEditDto) {
        return TitleConverter.mapTo(titlesService.create(titleEditDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TitleDto updateTitle(@PathVariable int id, @RequestBody TitleEditDto titleEditDto) {
        Account account = new Account();
        account.setId(1);

        return TitleConverter.mapTo(titlesService.update(id, titleEditDto, account.getId()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTitle(@PathVariable int id) {
        titlesService.delete(id);
    }
}
