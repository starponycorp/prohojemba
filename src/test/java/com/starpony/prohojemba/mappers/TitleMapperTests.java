package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.filters.TitlesFilter;
import com.starpony.prohojemba.models.Title;
import com.starpony.prohojemba.models.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Collections;
import java.util.List;


@SpringBootTest
@SqlGroup({
        @Sql(scripts = {
            "/sql/insert_types_data.sql",
            "/sql/insert_titles_data.sql",
            "/sql/insert_accounts_data.sql",
            "/sql/insert_accounttitles_data.sql"
        }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {
            "/sql/clear_titles_data.sql",
            "/sql/clear_types_data.sql",
            "/sql/clear_accounts_data.sql"
        }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public class TitleMapperTests {
    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void selectAllWithUserProgress_withoutFilter_returnTitles() {
        List<Title> expected_result = List.of(
                new Title(4, "Baldurs Gate 3", "/covers/2.webp", new Type(1, "Games")),
                new Title(12, "Better call Saul", "/covers/1.webp", new Type(3, "Series")),
                new Title(10, "Billy Herrington and Philosophic Cum", "/covers/1.webp", new Type(2, "Movies")),
                new Title(11, "Breaking Bad", "/covers/6.webp", new Type(3, "Series")),
                new Title(13, "FLCL", "/covers/1.webp", new Type(3, "Series"))
        );

        TitlesFilter titlesFilter = new TitlesFilter();
        String whereClause = titlesFilter.toWhereClause();
        Assertions.assertEquals(whereClause, ""); // Проверка правильной генерации sql оператора where

        List<Title> result = titleMapper.selectAllWithUserProgress(whereClause, 1, 5, 0);

        Assertions.assertIterableEquals(result, expected_result);
    }

    @Test
    public void selectAllWithUserProgress_filterByType_returnTitles() {
        List<Title> expected_result = List.of(
                new Title(12, "Better call Saul", "/covers/1.webp", new Type(3, "Series")),
                new Title(11, "Breaking Bad", "/covers/6.webp", new Type(3, "Series")),
                new Title(13, "FLCL", "/covers/1.webp", new Type(3, "Series")),
                new Title(14, "Steins;Gate", "/covers/1.webp", new Type(3, "Series"))
        );

        TitlesFilter titlesFilter = new TitlesFilter();
        titlesFilter.setType(3);
        String whereClause = titlesFilter.toWhereClause();
        Assertions.assertEquals(whereClause, "where titles.type = 3");

        List<Title> result = titleMapper.selectAllWithUserProgress(whereClause, 1, 5, 0);

        Assertions.assertIterableEquals(result, expected_result);
    }

    @Test
    public void selectAllWithUserProgress_filterByName_returnTitles() {
        List<Title> expected_result = List.of(
            new Title(3, "Persona 3 FES", null, new Type(1, "Games")),
            new Title(2, "Persona 5 Royal", "/covers/1.webp", new Type(1, "Games")),
            new Title(8, "Persona Movie", "/covers/1.webp", new Type(2, "Movies"))
        );

        TitlesFilter titlesFilter = new TitlesFilter();
        titlesFilter.setNameSearch("persona");
        String whereClause = titlesFilter.toWhereClause();
        Assertions.assertEquals(whereClause, "where titles.name ilike '%persona%'");

        List<Title> result = titleMapper.selectAllWithUserProgress(whereClause, 1, 5, 0);

        Assertions.assertIterableEquals(result, expected_result);
    }

    @Test
    public void selectAllWithUserProgress_filterByNameAndType_returnTitles() {
        List<Title> expected_result = List.of(
                new Title(3, "Persona 3 FES", null, new Type(1, "Games")),
                new Title(2, "Persona 5 Royal", "/covers/1.webp", new Type(1, "Games"))
        );

        TitlesFilter titlesFilter = new TitlesFilter();
        titlesFilter.setNameSearch("persona");
        titlesFilter.setType(1);
        String whereClause = titlesFilter.toWhereClause();
        Assertions.assertEquals(whereClause, "where titles.type = 1 and titles.name ilike '%persona%'");

        List<Title> result = titleMapper.selectAllWithUserProgress(whereClause, 1, 5, 0);

        Assertions.assertIterableEquals(result, expected_result);
    }

    @Test
    public void selectAllWithUserProgress_limit3AndOffset2_returnTitles() {
        List<Title> expected_result = List.of(
                new Title(10, "Billy Herrington and Philosophic Cum", "/covers/1.webp", new Type(2, "Movies")),
                new Title(11, "Breaking Bad", "/covers/6.webp", new Type(3, "Series")),
                new Title(13, "FLCL", "/covers/1.webp", new Type(3, "Series"))
        );

        TitlesFilter titlesFilter = new TitlesFilter();
        String whereClause = titlesFilter.toWhereClause();
        Assertions.assertEquals(whereClause, ""); // Проверка правильной генерации sql оператора where

        List<Title> result = titleMapper.selectAllWithUserProgress(whereClause, 1, 3, 2);

        Assertions.assertIterableEquals(result, expected_result);
    }

    @Test
    public void selectByIdWithUserProgress_idExists_returnTitle() {
        Title expected_result = new Title(11, "Breaking Bad", "/covers/6.webp", new Type(3, "Series"));
        expected_result.setProgress("COMPLETE");

        Title result = titleMapper.selectByIdWithUserProgress(11, 1);

        Assertions.assertEquals(result, expected_result);
        // Проверка пользовательского прогресса
        Assertions.assertEquals(result.getProgress(), expected_result.getProgress());
    }

    @Test
    public void selectByIdWithUserProgress_idNotExist_returnNull() {
        Title result = titleMapper.selectByIdWithUserProgress(20, 1);

        Assertions.assertNull(result);
    }

    @Test
    public void create_correctCreate() {
        Title title = new Title(0, "Cookie Clicker", null, new Type(1, "Games"));
        titleMapper.create(title);

        Assertions.assertNotEquals(title.getId(), 0);
    }

    @Test
    public void create_nameNull_throwException() {
        Title title = new Title(0, null, null, new Type(1, "Games"));

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> titleMapper.create(title));
    }

    @Test
    public void create_typeNull_correctCreate() {
        Title title = new Title(0, "Cookie Clicker", null, null);
        titleMapper.create(title);

        Assertions.assertNotEquals(title.getId(), 0);
    }

    @Test
    public void update_correctUpdate() {
        Title title = new Title(4, "Pathfinder", null, new Type(2, "Movies"));
        titleMapper.update(title);

        Title updatedTitle = titleMapper.selectByIdWithUserProgress(4, 1);

        Assertions.assertEquals(updatedTitle, title);
    }

    @Test
    public void update_nameNull_throwException() {
        Title title = new Title(4, null, null, new Type(2, "Movies"));
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> titleMapper.update(title));
    }

    @Test
    public void update_typeNull_correctUpdate() {
        Title title = new Title(4, "Pathfinder", null, null);
        titleMapper.update(title);

        Title updatedTitle = titleMapper.selectByIdWithUserProgress(4, 1);

        Assertions.assertEquals(updatedTitle, title);
    }

    @Test
    public void delete_correctDelete() {
        titleMapper.delete(4);

        Title deletedTitle = titleMapper.selectByIdWithUserProgress(4, 1);

        Assertions.assertNull(deletedTitle);
    }
}
