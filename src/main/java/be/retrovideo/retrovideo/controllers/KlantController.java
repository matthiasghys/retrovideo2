package be.retrovideo.retrovideo.controllers;

import be.retrovideo.retrovideo.forms.searchForm;
import be.retrovideo.retrovideo.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/klanten")
public class KlantController {

    private KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping()
    public ModelAndView index(){
        return new ModelAndView("klanten").addObject(new searchForm(""));
    }

    @GetMapping("/zoeken")
    public ModelAndView zoeken(@Valid searchForm form, Errors errors){
        ModelAndView modelAndView = new ModelAndView("klanten");
        modelAndView.addObject("zoekresultaten", klantService.findByZoekterm(form.getZoekterm()));
        return modelAndView;
    }

}
