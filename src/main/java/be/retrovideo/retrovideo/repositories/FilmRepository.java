package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    List<Film> findAll();
    Optional<Film> findById(long id);
    List<Film> findByGenre( long genreId);
}
