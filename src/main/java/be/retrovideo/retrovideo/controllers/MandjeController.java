package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.services.FilmService;
import be.retrovideo.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView mandje(){
        ModelAndView modelAndView = new ModelAndView("mandje");
        List<Film> alleFilms = filmService.findAll();
        modelAndView.addObject("filmsinmandje", alleFilms.stream().filter(film-> mandje.bevat(film.getId())).collect(Collectors.toList()));
        return modelAndView;
    }

    public @PostMapping
    String voegToe(Long id){
        mandje.voegToe(id);
        return "redirect:/mandje";
    }

    public @PostMapping("/verwijder")

    String verwijder(@RequestBody MultiValueMap<String, String> formData){
        for (List<String> values : formData.values()){
           for(String value: values){

               mandje.verwijder( Long.valueOf(value));
           }
        }
        return "redirect:/mandje";
    }
}
