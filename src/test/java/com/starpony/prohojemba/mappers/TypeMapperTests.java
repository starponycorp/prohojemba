package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.types.models.Type;

import com.starpony.prohojemba.types.repositories.mappers.TypeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;


@SpringBootTest
@SqlGroup({
        @Sql(scripts = {"/sql/insert_types_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"/sql/clear_types_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class TypeMapperTests {
    @Autowired
    private TypeMapper typeMapper;

    @Test
    public void selectAll_selectTypesList_returnTypesList() {
        List<Type> types = List.of(
                new Type(1, "Games"),
                new Type(2, "Movies"),
                new Type(3, "Series")
        );

        Assertions.assertIterableEquals(typeMapper.selectAll(), types);
    }

    @Test
    public void selectById_idExist_returnType() {
        Type type = new Type(3, "Series");

        Assertions.assertEquals(typeMapper.selectById(3), type);
    }

    @Test
    public void selectById_idNotExist_returnNull() {
        Assertions.assertNull(typeMapper.selectById(5));
    }

    @Test
    public void selectByViewName_viewNameExist_returnType() {
        Type type = new Type(2, "Movies");

        Assertions.assertEquals(typeMapper.selectByViewName("Movies"), type);
    }

    @Test
    public void selectByViewName_viewNameNotExist_returnNull() {
        Assertions.assertNull(typeMapper.selectByViewName("Test"));
    }

    @Test
    public void create_viewNameNotExist_correctCreate() {
        Type type = new Type(0, "Test");
        typeMapper.create(type);

        Assertions.assertNotEquals(type.getId(), 0);
    }

    @Test
    public void create_viewNameAlreadyExist_throwException() {
        Type type = new Type(0, "Games");

        Assertions.assertThrows(DuplicateKeyException.class, () -> typeMapper.create(type));
    }

    @Test
    public void create_viewNameNull_throwException() {
        Type type = new Type(0, null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> typeMapper.create(type));
    }

    @Test
    public void update_viewNameNotExist_correctUpdate() {
        Type type = new Type(3, "Test");
        typeMapper.update(type);

        Assertions.assertEquals(type, typeMapper.selectById(3));
    }

    @Test
    public void update_viewNameExist_throwException() {
        Type type = new Type(3, "Movies");

        Assertions.assertThrows(DuplicateKeyException.class, () -> typeMapper.update(type));
    }

    @Test
    public void update_viewNameNull_throwException() {
        Type type = new Type(3, null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> typeMapper.update(type));
    }

    @Test
    public void delete_correctDelete() {
        typeMapper.delete(3);

        Assertions.assertNull(typeMapper.selectById(3));
    }
}
