package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ModelAndView film(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("film");
        Optional<Film> optionalFilm = filmService.findById(id);
        optionalFilm.ifPresent(film -> modelAndView.addObject("film", film));
        return modelAndView;
    }
}
