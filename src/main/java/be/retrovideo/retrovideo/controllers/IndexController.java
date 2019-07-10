package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.services.FilmService;
import be.retrovideo.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final FilmService filmService;
    private final GenreService genreService;

    public IndexController(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("index", "films", filmService.findAll()).addObject("genres", genreService.findAll());
    }

}
