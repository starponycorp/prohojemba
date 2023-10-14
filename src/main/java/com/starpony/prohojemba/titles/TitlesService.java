package com.starpony.prohojemba.titles;

import com.starpony.prohojemba.dto.TitleEditDto;
import com.starpony.prohojemba.enums.TitleProgress;
import com.starpony.prohojemba.exceptions.ItemFormException;
import com.starpony.prohojemba.exceptions.ItemNotFoundException;
import com.starpony.prohojemba.filters.TitlesFilter;
import com.starpony.prohojemba.models.Title;
import com.starpony.prohojemba.types.models.Type;
import com.starpony.prohojemba.repositories.TitlesRepository;
import com.starpony.prohojemba.types.repositories.TypesDatabaseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    public List<Title> getAll(@Valid TitlesFilter titlesFilter,
                              @Min(value = 5, message = "Limit min value is 5") @Max(value = 100, message = "Limit max value is 100") int limit,
                              @Min(value = 0, message = "Offset cannot be less than 0") int offset,
                              int userId) {
        return titlesRepository.getAll(titlesFilter, limit, offset, userId);
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
