package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.services.FilmService;
import be.retrovideo.retrovideo.services.KlantService;
import be.retrovideo.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("reserveren").addObject("aantalfilms", mandje.aantalFilmsInMandje());

    }
}
