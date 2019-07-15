package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcKlantRepository implements KlantRepository {

    private final JdbcTemplate template;
    private final RowMapper<Klant> klantRowMapper = (result, rowNum)->
            new Klant(result.getString("familienaam"), result.getString("voornaam"),
                    result.getString("straatnummer"), result.getInt("postcode"), result.getString("gemeente"), result.getLong("id"));

    public JdbcKlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Klant> findById(long id) {
    try{
        String sql = "select id, familienaam, voornaam, straatnummer, postcode, gemeente from klanten where id = ?";
        return Optional.of(template.queryForObject(sql, klantRowMapper, id));
    }catch(IncorrectResultSizeDataAccessException ex) {
        return Optional.empty();
    }
    }

    @Override
    public List<Klant> findByZoekterm(String zoekterm) {


            String zoekterm2= "%" + zoekterm + "%";
            String sql = "select id, familienaam, voornaam, straatnummer, postcode, gemeente from klanten where familienaam like ?";
            return template.query(sql, klantRowMapper, zoekterm2);

    }
}
