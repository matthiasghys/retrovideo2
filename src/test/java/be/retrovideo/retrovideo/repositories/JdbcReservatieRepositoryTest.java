package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.domain.Klant;
import be.retrovideo.retrovideo.domain.Reservatie;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcReservatieRepository.class)
@Sql("/insertfilms.sql")

public class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcReservatieRepository repository;

    @Test
    public void create(){
        LocalDateTime nu = LocalDateTime.now();
        repository.create(new Reservatie(new Klant("test", "test", "test 1", 2160, "test", 50),
                new Film("test", 12, 6, BigDecimal.valueOf(5), 9, 200), nu));
        assertThat(super.countRowsInTableWhere("reservaties", "filmid=200")).isOne();
    }

}
