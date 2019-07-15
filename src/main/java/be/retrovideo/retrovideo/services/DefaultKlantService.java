package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Klant;
import be.retrovideo.retrovideo.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class DefaultKlantService implements KlantService{

    private final KlantRepository repository;

    public DefaultKlantService(KlantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Klant> findById(long id){
        return repository.findById(id);
    };


    @Override
    public List<Klant> findByZoekterm(String zoekterm) {
        return repository.findByZoekterm(zoekterm);
    }
}
