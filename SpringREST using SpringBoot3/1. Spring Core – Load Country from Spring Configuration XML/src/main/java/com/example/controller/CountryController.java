package com.example.controller;

import com.example.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    @Qualifier("india")
    private Country india;

    @Autowired
    @Qualifier("usa")
    private Country usa;

    @GetMapping("/india")
    public Country getIndia() {
        logger.trace("TRACE level log inside getIndia()");
        logger.debug("DEBUG level log inside getIndia()");
        logger.info("INFO level log inside getIndia()");
        logger.warn("WARN level log inside getIndia()");
        logger.error("ERROR level log inside getIndia()");
        return india;
    }

    @GetMapping("/usa")
    public Country getUsa() {
        logger.trace("TRACE level log inside getUsa()");
        logger.debug("DEBUG level log inside getUsa()");
        logger.info("INFO level log inside getUsa()");
        logger.warn("WARN level log inside getUsa()");
        logger.error("ERROR level log inside getUsa()");
        return usa;
    }
}
