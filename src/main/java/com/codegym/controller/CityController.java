package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Page<Country> countries(Pageable pageable){
        return countryService.findAll(pageable);
    }

    @GetMapping("/city")
    public ModelAndView listCity(@PageableDefault(size = 3) Pageable pageable){
        Page<City> cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/city/List");
        modelAndView.addObject("city",cities);
        return modelAndView;
    }

    @GetMapping("detail-city/{id}")
    public ModelAndView detail(@PathVariable Long id , HttpServletRequest httpServletRequest){
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/Detail");
        modelAndView.addObject("city",city);
        String r = httpServletRequest.getHeader("Referer");
        modelAndView.addObject("referer",r);
        return modelAndView;
    }

}
