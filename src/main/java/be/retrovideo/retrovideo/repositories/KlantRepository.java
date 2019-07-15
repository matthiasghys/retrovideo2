package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantRepository {

    Optional<Klant> findById(long id);
    List<Klant> findByZoekterm(String zoekterm);

}
