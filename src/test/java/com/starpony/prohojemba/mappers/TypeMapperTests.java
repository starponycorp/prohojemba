package com.starpony.prohojemba.mappers;

import com.starpony.prohojemba.types.Type;
import com.starpony.prohojemba.types.TypeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;


@SpringBootTest
@SqlGroup({
        @Sql(scripts = {"/sql/insert_types_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"/sql/clear_types_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class TypeMapperTests {
    @Autowired
    private TypeMapper typeMapper;

    @Test
    public void selectById_findTypeWithId3_returnType() {
        Type type = new Type();
        type.setId(3);
        type.setViewName("Series");

        Type searchedType = typeMapper.selectById(3);

        Assertions.assertEquals(searchedType, type);
    }

    @Test
    public void selectById_findTypeWithId5_returnNull() {
        Type searchedType = typeMapper.selectById(5);

        Assertions.assertNull(searchedType);
    }
}
