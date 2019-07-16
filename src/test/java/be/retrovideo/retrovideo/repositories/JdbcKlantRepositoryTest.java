package be.retrovideo.retrovideo.repositories;

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
@Import(JdbcKlantRepository.class)
@Sql("/insertFilms.sql")
public class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private KlantRepository repository;

    @Test
    public void findByZoekterm(){
        assertThat(repository.findByZoekterm("test")).isEqualTo("test");
    }

    long idVanTestKlant(){
        return super.jdbcTemplate.queryForObject("select id from klanten where voornaam = 'test'", Long.class);
    }
    @Test
    public void findById(){
        assertThat(repository.findById(idVanTestKlant()).get().getVoorNaam()).isEqualTo("test");
    }

}
