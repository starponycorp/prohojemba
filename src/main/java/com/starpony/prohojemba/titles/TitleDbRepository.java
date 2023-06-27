package com.starpony.prohojemba.titles;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.*;


@Repository
public class TitleDbRepository implements TitleRepository{
    private final TitleMapper titleMapper;
    private final TitlesTable titlesTable = new TitlesTable();

    @Autowired
    public TitleDbRepository(TitleMapper titleMapper) {
        this.titleMapper = titleMapper;
    }

    @Override
    public List<Title> findAll(QueryParams queryParams) {
        WhereDSL.StandaloneWhereFinisher whereFinisher = null;
        if (queryParams.getType() != 0) whereFinisher = where(titlesTable.type, isEqualTo(queryParams.getType()));

        if (queryParams.getSearch() != null) {
            if (whereFinisher == null) {
                whereFinisher = where(titlesTable.name, isLikeCaseInsensitive("%" + queryParams.getSearch() + "%"));
            } else {
                whereFinisher.and(titlesTable.name, isLikeCaseInsensitive("%" + queryParams.getSearch() + "%"));
            }
        }

        WhereClauseProvider whereClause = whereFinisher!=null?whereFinisher.build()
                .render(RenderingStrategies.MYBATIS3, "whereClauseProvider").orElseThrow()
                :WhereClauseProvider.withWhereClause(" ").build();

        return titleMapper.selectAll(whereClause, queryParams.getLimit(), queryParams.getOffset());
    }

    @Override
    public Optional<Title> find(int id) {
        return Optional.ofNullable(titleMapper.selectById(id));
    }

    @Override
    public void create(Title title) {
        titleMapper.create(title);
    }

    @Override
    public void update(Title title) {
        titleMapper.update(title);
    }

    @Override
    public void delete(int id) {
        titleMapper.delete(id);
    }

    // Описание таблицы Titles, для генерации динамического sql
    private static final class TitlesTable extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);
        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
        public final SqlColumn<String> cover = column("cover", JDBCType.VARCHAR);
        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public TitlesTable() {
            super("titles");
        }
    }
}
