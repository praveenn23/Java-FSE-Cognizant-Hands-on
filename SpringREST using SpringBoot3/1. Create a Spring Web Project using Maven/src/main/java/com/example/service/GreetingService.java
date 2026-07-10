package com.example.service;

public class GreetingService {

    private final String greeting;
    private final String language;

    public GreetingService(String greeting, String language) {
        this.greeting = greeting;
        this.language = language;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getLanguage() {
        return language;
    }

    public String greet(String name) {
        return greeting + ", " + name + "! (Language: " + language + ")";
    }

    @Override
    public String toString() {
        return "GreetingService{greeting='" + greeting + "', language='" + language + "'}";
    }
}