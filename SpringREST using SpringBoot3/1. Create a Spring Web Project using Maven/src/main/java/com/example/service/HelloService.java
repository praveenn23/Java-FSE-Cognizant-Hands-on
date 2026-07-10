package com.example.service;

public class HelloService {

    private String message;
    private String version;

    public HelloService() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public String getVersion() {
        return version;
    }

    public String sayHello() {
        return message + " (v" + version + ")";
    }

    @Override
    public String toString() {
        return "HelloService{message='" + message + "', version='" + version + "'}";
    }
}