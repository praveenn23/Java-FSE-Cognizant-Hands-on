package com.example.runner;

import com.example.service.GreetingService;
import com.example.service.HelloService;
import com.example.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanLoadingDemoRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BeanLoadingDemoRunner.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("  SPRING BEAN LOADING & CONFIGURATION DEMO");

        System.out.println("--- 1. Loading beans from spring-config.xml ---");
        System.out.println("    Using: ClassPathXmlApplicationContext\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        System.out.println("--- 2. Singleton Scope + Setter Injection ---");
        System.out.println("    Bean: helloService (scope=\"singleton\")\n");

        HelloService hello1 = (HelloService) context.getBean("helloService");
        HelloService hello2 = (HelloService) context.getBean("helloService");

        System.out.println("    hello1 = " + hello1.sayHello());
        System.out.println("    hello2 = " + hello2.sayHello());
        System.out.println("    hello1 == hello2 ? " + (hello1 == hello2));
        System.out.println("    >>> SINGLETON: Same instance returned both times!\n");

        logger.info("Singleton demo - hello1 hashCode: {}", System.identityHashCode(hello1));
        logger.info("Singleton demo - hello2 hashCode: {}", System.identityHashCode(hello2));

        System.out.println("--- 3. Prototype Scope + Constructor Injection ---");
        System.out.println("    Bean: greetingService (scope=\"prototype\")\n");

        GreetingService greet1 = (GreetingService) context.getBean("greetingService");
        GreetingService greet2 = (GreetingService) context.getBean("greetingService");

        System.out.println("    greet1 = " + greet1.greet("Alice"));
        System.out.println("    greet2 = " + greet2.greet("Bob"));
        System.out.println("    greet1 == greet2 ? " + (greet1 == greet2));
        System.out.println("    >>> PROTOTYPE: Different instance each time!\n");

        logger.info("Prototype demo - greet1 hashCode: {}", System.identityHashCode(greet1));
        logger.info("Prototype demo - greet2 hashCode: {}", System.identityHashCode(greet2));

        System.out.println("--- 4. Setter Injection with Bean Reference ---");
        System.out.println("    Bean: notificationService (ref=\"helloService\")\n");

        NotificationService notifService = (NotificationService) context.getBean("notificationService");
        System.out.println("    " + notifService.sendNotification("admin@company.com"));
        System.out.println("    NotificationService uses HelloService internally (via ref)\n");

        System.out.println("--- 5. Logging with SLF4J (LoggerFactory + Logger) ---");
        System.out.println("    Log levels: TRACE < DEBUG < INFO < WARN < ERROR\n");

        logger.trace("TRACE - Most detailed level (enable with logging.level.com.example=TRACE)");
        logger.debug("DEBUG - Diagnostic information for debugging");
        logger.info("INFO  - General informational messages (default level)");
        logger.warn("WARN  - Potentially harmful situation, something to watch");
        logger.error("ERROR - Error event, but application may still continue");

        System.out.println("\n    Check the console above for log output!");
        System.out.println("    (TRACE may not appear if logging.level is set to DEBUG or higher)");

        System.out.println("  SUMMARY:");
        System.out.println("  - Beans loaded from: spring-config.xml");
        System.out.println("  - IoC Container: ClassPathXmlApplicationContext");
        System.out.println("  - Singleton scope: Same object every time");
        System.out.println("  - Prototype scope: New object every time");
        System.out.println("  - Setter Injection: <property name='...' value='...'/>  ");
        System.out.println("  - Constructor Injection: <constructor-arg value='...'/>");
        System.out.println("  - Logging: LoggerFactory.getLogger() + logger.info()");
        System.out.println("  REST Endpoints:");
        System.out.println("  - GET http://localhost:8080/hello");
        System.out.println("  - GET http://localhost:8080/greet?name=Prax");
        System.out.println("  - GET http://localhost:8080/notify?to=user@email.com");

        ((ClassPathXmlApplicationContext) context).close();
    }
}