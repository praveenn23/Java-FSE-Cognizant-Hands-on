package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;

/**
 * REST - Country Web Service.
 *
 * GET /country    -> returns India country details (loaded from Spring XML bean config)
 * GET /countries  -> returns the full list of countries (loaded from country.xml)
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final Country india;
    private final List<Country> countryList;

    public CountryController(@Qualifier("india") Country india,
                              @Qualifier("countryList") List<Country> countryList) {
        this.india = india;
        this.countryList = countryList;
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

}
