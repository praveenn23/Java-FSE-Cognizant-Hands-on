package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private final List<Country> countryList;

    public CountryService(@Qualifier("countryList") List<Country> countryList) {
        this.countryList = countryList;
    }

    /**
     * Returns the country matching the given code (case insensitive).
     * Throws CountryNotFoundException if no match is found.
     */
    public Country getCountry(String code) {
        LOGGER.info("start getCountry({})", code);

        Country country = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException(code));

        LOGGER.info("end getCountry({})", code);
        return country;
    }

}
