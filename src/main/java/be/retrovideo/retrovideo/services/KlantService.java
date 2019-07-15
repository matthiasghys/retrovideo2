package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantService {

Optional<Klant> findById(long id);
    List<Klant> findByZoekterm(String zoekterm);
}
