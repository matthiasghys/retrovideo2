package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.domain.Klant;
import be.retrovideo.retrovideo.domain.Reservatie;
import be.retrovideo.retrovideo.exceptions.ReservatieMisluktException;
import be.retrovideo.retrovideo.services.FilmService;
import be.retrovideo.retrovideo.services.KlantService;
import be.retrovideo.retrovideo.services.ReservatieService;
import be.retrovideo.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reserveren")
public class ReservatieController {

    private final KlantService klantService;
    private final FilmService filmService;
    private Mandje mandje;
    private final ReservatieService reservatieService;

    public ReservatieController(KlantService klantService, FilmService filmService, Mandje mandje, ReservatieService reservatieService) {
        this.klantService = klantService;
        this.filmService = filmService;
        this.mandje = mandje;
        this.reservatieService= reservatieService;
    }

    @GetMapping("{id}")
    public ModelAndView reservatie(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("reserveren");
        Optional<Klant> optionalKlant = klantService.findById(id);
        optionalKlant.ifPresent(klant -> modelAndView.addObject("klant", klant));
        return modelAndView.addObject("aantalfilms", mandje.aantalFilmsInMandje());

    }

    @PostMapping
    public ModelAndView reserveren(@RequestBody String klantId){
        String[] stukjes= klantId.split("=");
        ModelAndView modelAndView= new ModelAndView("rapport");
        List<Film> alleFilms = filmService.findAll();
        List<Film> alleFilmsInMandje= alleFilms.stream().filter(film-> mandje.bevat(film.getId())).collect(Collectors.toList());
        Optional<Klant> optionalKlant = klantService.findById(Long.valueOf(stukjes[1]));
        Klant klant = optionalKlant.get();
        List<Film> slechteFilms = new ArrayList<>();
        for(Film film:alleFilmsInMandje){
            Reservatie reservatie = new Reservatie(klant, film, LocalDateTime.now());
            try{
                reservatieService.create(reservatie);
            } catch(ReservatieMisluktException ex){
                slechteFilms.add(film);
            }
        }
        return modelAndView.addObject("slechteFilms", slechteFilms);

    }
}
