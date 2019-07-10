package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.domain.Genre;
import be.retrovideo.retrovideo.services.FilmService;
import be.retrovideo.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/genre")
public class GenreController {
    private final FilmService filmService;
    private final GenreService genreService;

    public GenreController(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @GetMapping("{id}")
    public ModelAndView genre(@PathVariable long id){
        ModelAndView mv = new ModelAndView("genre", "films", filmService.findByGenre(id));
        Optional<Genre> optionalGenre = genreService.findById(id);
        optionalGenre.ifPresent(genre -> mv.addObject("genre", genre));
        return mv;
    }

}
