package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> findAll();
    Optional<Film> findById(long id);
    List<Film> findByGenre( long genreId);
}
