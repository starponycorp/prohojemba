package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.titles.exceptions.TitleNotFoundException;
import com.starpony.prohojemba.types.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TitleService {
    private final TitleRepository titleRepository;

    private final TypeService typeService;

    @Autowired
    public TitleService(TitleRepository titleRepository, TypeService typeService) {
        this.titleRepository = titleRepository;
        this.typeService = typeService;
    }

    public List<Title> getAll(QueryParams queryParams) {
        List<Title> titles = titleRepository.findAll(queryParams);
        // Todo добавить заполнение статуса тайтлов для пользователя
        return titles;
    }

    public Title getOne(int id) {
        Title title = titleRepository.find(id).orElseThrow(() ->
                new TitleNotFoundException(String.format("Title with id=%s not found", id)));
        // Todo добавить заполнение статуса тайтла для пользователя
        return title;
    }

    public void create(Title title) {
        title.setType(typeService.getOne(title.getType().getId()));
        titleRepository.create(title);
    }

    public void update(Title title) {
        title.setType(typeService.getOne(title.getType().getId()));
        titleRepository.update(title);
    }

    public void delete(int id) {
        titleRepository.delete(id);
    }
}
