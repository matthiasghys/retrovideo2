package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Film;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JdbcFilmRepository implements FilmRepository{


    private final JdbcTemplate template;
    private final RowMapper<Film> filmRowMapper = (result, rowNum)->
            new Film(result.getString("titel"), result.getInt("voorraad"), result.getInt("gereserveerd"),
                    result.getBigDecimal("prijs"), result.getLong("genreid"), result.getLong("id"));

    public JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Film> findAll() {
       String sql = "Select id, genreid, titel, voorraad, gereserveerd, prijs from films order by id";
       return template.query(sql, filmRowMapper);
    }

    @Override
    public Optional<Film> findById(long id) {
        try{
            String sql= "Select id, genreid, titel, voorraad, gereserveerd, prijs from films where id=?";
            return Optional.of(template.queryForObject(sql, filmRowMapper , id));
        }catch(IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findByGenre( long genreid) {
        String sql = "Select id, genreid, titel, voorraad, gereserveerd, prijs from films where genreid = ?  ";
        return template.query(sql, filmRowMapper, genreid);
    }
}
