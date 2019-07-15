package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Klant;
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
    public void findAll(){
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable("klanten"))
                .extracting(Klant::getId).isSorted();
    }


    @Test
    public void findByZoekterm(){
        assertThat(repository.findByZoekterm("test")).isEqualTo("test");
    }
}
