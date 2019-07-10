package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class DefaultFilmService implements FilmService {
    private final FilmRepository repository;

    public DefaultFilmService(FilmRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
    public List<Film> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
    public Optional<Film> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
    public List<Film> findByGenre(long genreId) {
        return repository.findByGenre(genreId);
    }
}
