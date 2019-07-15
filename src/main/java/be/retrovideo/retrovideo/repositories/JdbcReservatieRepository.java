package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Reservatie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository{

    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;


    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.withTableName("reservaties");
        insert.usingGeneratedKeyColumns("id");
    }

    @Override
    public void create(Reservatie reservatie) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("klantid", reservatie.getKlant().getId());
        kolomWaarden.put("filmid", reservatie.getFilm().getId());
        kolomWaarden.put("reservatie", reservatie.getReservatie());
        insert.execute(kolomWaarden);

    }
}
