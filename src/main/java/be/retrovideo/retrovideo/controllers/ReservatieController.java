package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.domain.Klant;
import be.retrovideo.retrovideo.services.FilmService;
import be.retrovideo.retrovideo.services.KlantService;
import be.retrovideo.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/reserveren")
public class ReservatieController {

    private final KlantService klantService;
    private final FilmService filmService;
    private Mandje mandje;

    public ReservatieController(KlantService klantService, FilmService filmService, Mandje mandje) {
        this.klantService = klantService;
        this.filmService = filmService;
        this.mandje = mandje;
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
        ModelAndView modelAndView= new ModelAndView("rapport");

        return modelAndView;

    }
}
