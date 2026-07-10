package com.example.service;

public class NotificationService {

    private HelloService helloService;
    private String notificationType;

    public NotificationService() {
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public HelloService getHelloService() {
        return helloService;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public String sendNotification(String recipient) {
        return "Sending " + notificationType + " notification to " + recipient
                + " with message: " + helloService.sayHello();
    }

    @Override
    public String toString() {
        return "NotificationService{type='" + notificationType
                + "', helloService=" + helloService + "}";
    }
}