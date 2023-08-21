package com.starpony.prohojemba.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;


@SpringBootTest
@SqlGroup({
        @Sql(scripts = {"/sql/insert_types_data.sql", "/sql/insert_titles_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"/sql/clear_titles_data.sql", "/sql/clear_types_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public class TitleMapperTests {
    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void selectAllWithUserProgress_withoutFilter_returnTitles() {

    }

    @Test
    public void selectAllWithUserProgress_filterByType_returnTitles() {

    }


}
