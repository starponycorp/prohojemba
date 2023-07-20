package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.titles.exceptions.TitleNotFoundException;
import com.starpony.prohojemba.types.TypeRepository;
import com.starpony.prohojemba.types.TypeService;
import com.starpony.prohojemba.types.exceptions.TypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Validated
public class TitleService {
    private final TitleRepository titleRepository;
    private final TitleStateRepository titleStateRepository;

    private final TypeRepository typeRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository, TitleStateRepository titleStateRepository, TypeRepository typeRepository) {
        this.titleRepository = titleRepository;
        this.titleStateRepository = titleStateRepository;
        this.typeRepository = typeRepository;
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
        title.setType(typeRepository.findOne(title.getType().getId()).orElseThrow(() ->
                new TypeNotFoundException(String.format("Type with id=%s not found", title.getType().getId()))));
        titleRepository.create(title);
    }

    public void update(Title title) {
        title.setType(typeRepository.findOne(title.getType().getId()).orElseThrow(() ->
                new TypeNotFoundException(String.format("Type with id=%s not found", title.getType().getId()))));
        titleRepository.update(title);
    }

    public void updateStatusForUser(int accountId, int titleId, TitleStateForUser state) {
        titleStateRepository.createOrUpdate(accountId, titleId, state);
    }

    public void delete(int id) {
        titleRepository.delete(id);
    }
}
