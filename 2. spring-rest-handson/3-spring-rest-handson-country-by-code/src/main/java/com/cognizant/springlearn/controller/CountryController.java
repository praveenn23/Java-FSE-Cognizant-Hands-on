package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

/**
 * REST - Country Web Service (+ get country based on country code).
 *
 * GET /country          -> returns India country details (loaded from Spring XML bean config)
 * GET /countries        -> returns the full list of countries (loaded from country.xml)
 * GET /countries/{code} -> returns the country matching the given code (case insensitive)
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final Country india;
    private final List<Country> countryList;
    private final CountryService countryService;

    public CountryController(@Qualifier("india") Country india,
                              @Qualifier("countryList") List<Country> countryList,
                              CountryService countryService) {
        this.india = india;
        this.countryList = countryList;
        this.countryService = countryService;
        LOGGER.info("CountryController constructed");
    }

    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("start getCountryIndia()");
        LOGGER.info("end getCountryIndia()");
        return india;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("start getAllCountries()");
        LOGGER.info("end getAllCountries()");
        return countryList;
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("start getCountry({})", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("end getCountry({})", code);
        return country;
    }

}
