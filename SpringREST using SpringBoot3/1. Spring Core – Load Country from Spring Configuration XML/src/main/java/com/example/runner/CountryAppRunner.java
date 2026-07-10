package com.example.runner;

import com.example.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CountryAppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CountryAppRunner.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Loading beans from spring-config.xml ---");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        Country country1 = (Country) context.getBean("india");
        Country country2 = (Country) context.getBean("india");

        System.out.println("country1 = " + country1);
        System.out.println("country2 = " + country2);
        System.out.println("country1 == country2 ? " + (country1 == country2));

        Country another1 = (Country) context.getBean("usa");
        Country another2 = (Country) context.getBean("usa");

        System.out.println("another1 = " + another1);
        System.out.println("another2 = " + another2);
        System.out.println("another1 == another2 ? " + (another1 == another2));

        logger.trace("TRACE level - CountryAppRunner");
        logger.debug("DEBUG level - CountryAppRunner");
        logger.info("INFO level - CountryAppRunner");
        logger.warn("WARN level - CountryAppRunner");
        logger.error("ERROR level - CountryAppRunner");

        ((ClassPathXmlApplicationContext) context).close();
    }
}
