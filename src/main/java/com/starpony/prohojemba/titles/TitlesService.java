package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.titles.dto.TitleEditDto;
import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.titles.models.Title;
import com.starpony.prohojemba.titles.repositories.filters.TitlesListFilter;
import com.starpony.prohojemba.types.models.Type;
import com.starpony.prohojemba.titles.repositories.TitlesRepository;
import com.starpony.prohojemba.types.repositories.TypesDatabaseRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Validated
public class TitlesService {
    private final TitlesRepository titlesRepository;
    private final TypesDatabaseRepository typesRepository;

    @Autowired
    public TitlesService(TitlesRepository titlesRepository, TypesDatabaseRepository typesRepository) {
        this.titlesRepository = titlesRepository;
        this.typesRepository = typesRepository;
    }

    public List<Title> getAll(@Valid TitlesListFilter titlesFilter) {
        return titlesRepository.getAll(titlesFilter);
    }

    public Title getOne(int titleId, int userId) {
            return titlesRepository.getById(titleId, userId).orElseThrow(() ->
                new ItemNotFoundException(String.format("Title with id=%s not found", userId)));
    }

    public Title create(TitleEditDto titleEditDto) {
        Type titleType = typesRepository.getById(titleEditDto.getType()).orElseThrow(() ->
                new ItemFormException(String.format("Type with id=%s not found", titleEditDto.getType())));

        Title title = new Title(0, titleEditDto.getName(), titleEditDto.getCover(), titleType);

        titlesRepository.create(title);

        return title;
    }

    public Title update(int id, TitleEditDto titleEditDto, int userId) {
        titlesRepository.update(new Title(id, titleEditDto.getName(), titleEditDto.getCover(), new Type(titleEditDto.getType(), null)));

        return titlesRepository.getById(id, userId).orElseThrow(() ->
                new ItemNotFoundException(String.format("Title with id=%s not found", userId)));
    }

    public void updateProgressForUser(int userId, int titleId, TitleProgress progress) {
        titlesRepository.updateProgress(userId, titleId, progress);
    }

    public void delete(int id) {
        titlesRepository.delete(id);
    }
}
