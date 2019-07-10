package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcGenreRepository.class)
@Sql("/insertFilms.sql")
public class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private GenreRepository repository;

    @Test
    public void findAll(){
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable("genres"))
                .extracting(Genre::getId).isSorted();
    }
    long idVanTestGenre(){
        return super.jdbcTemplate.queryForObject("select id from genres where naam = 'test'", Long.class);
    }
    @Test
    public void findById(){
        assertThat(repository.findById(idVanTestGenre()).get().getNaam()).isEqualTo("test");
    }


}
