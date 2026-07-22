package com.example.controller;

import com.example.service.GreetingService;
import com.example.service.HelloService;
import com.example.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    @Qualifier("helloService")
    private HelloService helloService;

    @Autowired
    @Qualifier("greetingService")
    private GreetingService greetingService;

    @Autowired
    @Qualifier("notificationService")
    private NotificationService notificationService;

    @GetMapping("/hello")
    public String hello() {
        
        logger.trace("TRACE level - Most detailed logging");
        logger.debug("DEBUG level - Entering /hello endpoint");
        logger.info("INFO level - Processing hello request");
        logger.warn("WARN level - This is a warning example");
        logger.error("ERROR level - This is an error example");

        logger.info("HelloService (Setter Injection, Singleton): {}", helloService.sayHello());
        return helloService.sayHello();
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "World") String name) {
        logger.debug("Entering /greet endpoint with name: {}", name);
        logger.info("GreetingService (Constructor Injection, Prototype): {}", greetingService);

        String result = greetingService.greet(name);
        logger.info("Greeting result: {}", result);
        return result;
    }

    @GetMapping("/notify")
    public String notify(@RequestParam(defaultValue = "admin@company.com") String to) {
        logger.debug("Entering /notify endpoint with recipient: {}", to);
        logger.info("NotificationService: {}", notificationService);

        String result = notificationService.sendNotification(to);
        logger.info("Notification result: {}", result);
        return result;
    }
}