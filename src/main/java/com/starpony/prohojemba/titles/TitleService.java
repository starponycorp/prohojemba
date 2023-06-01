package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.titles.exceptions.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TitleService {
    private final TitleRepository titleRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public List<Title> getAll(QueryParams queryParams) {
        return titleRepository.findAll(queryParams);
    }

    public Title get(int id) {
        return titleRepository.find(id).orElseThrow(() ->
                new TitleNotFoundException(String.format("Title with id=%s not found", id)));
    }

    public void create(Title title) {
        // Получить информацию о типе тайтла и заполнить поле
        titleRepository.create(title);
    }

    public void update(Title title) {
        // Получить информацию о типе тайтла и заполнить поле
        titleRepository.update(title);
    }

    public void delete(int id) {
        titleRepository.delete(id);
    }
}
