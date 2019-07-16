package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.domain.Reservatie;
import be.retrovideo.retrovideo.exceptions.ReservatieMisluktException;
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
        if(reservatie.getFilm().isBeschikbaar()) {
            Map<String, Object> kolomWaarden = new HashMap<>();
            kolomWaarden.put("klantid", reservatie.getKlant().getId());
            kolomWaarden.put("filmid", reservatie.getFilm().getId());
            kolomWaarden.put("reservatie", reservatie.getReservatie());
            insert.execute(kolomWaarden);
            updateGereserveerdeFilm(reservatie.getFilm());
        }else throw new ReservatieMisluktException();
    }

    @Override
    public void updateGereserveerdeFilm(Film film) {
        String sql="update films set gereserveerd = ? where id = ?";
        template.update(sql, film.getGereserveerd()+1 , film.getId());
    }

}
