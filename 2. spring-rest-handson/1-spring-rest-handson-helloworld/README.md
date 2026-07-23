# spring-rest-handson — Hello World RESTful Web Service

## Objective
Write a REST service that returns the text `Hello World!!` using the Spring Web Framework.

## Details

| Method | GET |
| URL | `/hello` |
| Controller | `com.cognizant.springlearn.controller.HelloController` |
| Method | `public String sayHello()` |
| Implementation | Returns the hard-coded string `Hello World!!` |

## Run
```bash
mvn spring-boot:run
```
The app starts on port `8083` (see `src/main/resources/application.properties`).

## Try it
- Browser / curl: http://localhost:8083/hello
- Postman: `GET http://localhost:8083/hello`

Check the console log for the `start sayHello()` / `end sayHello()` lines, and inspect the
response headers in the browser Network tab (F12) or Postman's "Headers" tab.

## Test
```bash
mvn clean test
```
