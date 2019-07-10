package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcFilmRepository.class)
@Sql("/insertfilms.sql")
public class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private FilmRepository repository;
    private final RowMapper<Film> filmRowMapper = (result, rowNum)->
            new Film(result.getString("titel"), result.getInt("voorraad"), result.getInt("gereserveerd"),
                    result.getBigDecimal("prijs"), result.getLong("genreid"), result.getLong("id"));
    @Test
    public void findAll(){
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable("films"))
                .extracting(Film::getId).isSorted();
    }

    long idVanTestFilm(){
        return super.jdbcTemplate.queryForObject("select id from films where titel = 'test'", Long.class);
    }
    @Test
    public void findById(){
        assertThat(repository.findById(idVanTestFilm()).get().getTitel()).isEqualTo("test");
    }

    @Test
    public void findByOnbestaandeId(){
        assertThat(repository.findById(-1)).isEmpty();

    }
    List<Film> filmsPergenre(){
        return super.jdbcTemplate.query("id, genreid, titel, voorraad, gereserveerd, prijs from films where genreid = 10 ", filmRowMapper);
    }
    @Test
    public void findByGenre(){
        assertThat(repository.findByGenre(200).size()).isEqualTo(2);
    }

    @Test
    public void findByOnbestaandGenre(){
        assertThat(repository.findByGenre(-3)).isEmpty();
    }





}
