package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Genre;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcGenreRepository implements GenreRepository{

    private final JdbcTemplate template;
    private final RowMapper<Genre> genreRowMapper = (result, rowNum)
            -> new Genre(result.getString("naam"), result.getLong("id"));

    public JdbcGenreRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Genre> findAll() {
        String sql = "select id, naam from genres order by id";
        return template.query(sql, genreRowMapper);
    }

    @Override
    public Optional<Genre> findById(long id) {
        try{
            String sql= "Select id, naam from genres where genres.id = ?";
            return Optional.of(template.queryForObject(sql, genreRowMapper , id));
        }catch(IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
