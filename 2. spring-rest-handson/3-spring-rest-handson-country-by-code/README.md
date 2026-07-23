# spring-rest-handson — REST - Get country based on country code

## Objective
Write a REST service that returns a specific country based on country code. The country
code should be case insensitive. Also handle the exceptional scenario where the code does
not exist.

## Endpoint

### GET /countries/{code}
| | |
|---|---|
| Controller | `com.cognizant.springlearn.controller.CountryController` |
| Method Annotation | `@GetMapping("/countries/{code}")` |
| Method | `getCountry(String code)` |
| Implementation | Delegates to `countryService.getCountry(code)` |
| Service | `com.cognizant.springlearn.service.CountryService.getCountry(String code)` |

`CountryService.getCountry()`:
1. Gets the country code from `@PathVariable`.
2. Gets the country list (loaded from `country.xml`).
3. Uses a lambda / stream to do a case-insensitive match on the code.
4. Returns the matching country, or throws `CountryNotFoundException` if not found.

Sample request: `http://localhost:8083/countries/in`
```json
{
  "code": "IN",
  "name": "India"
}
```

## Exceptional scenario
`com.cognizant.springlearn.service.exception.CountryNotFoundException` is annotated with:
```java
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
```
`CountryService.getCountry()` throws this exception when the code doesn't match any country,
and `CountryController.getCountry()` declares `throws CountryNotFoundException`, so Spring
responds with a `404` and a JSON error body.

Sample request: `http://localhost:8083/countries/az`
```json
{
  "timestamp": "2019-10-02T03:27:54.521+0000",
  "status": 404,
  "error": "Not Found",
  "message": "Country not found",
  "path": "/countries/az"
}
```

Try it with curl:
```bash
curl -i http://localhost:8083/countries/az
```

## Run
```bash
mvn spring-boot:run
```
App runs on port `8083`.

## Test
MockMVC tests in `SpringLearnApplicationTests`:
- `testGetCountry` — GET /country returns 200 with code `IN` / name `India`.
- `testGetCountryByCode` — GET /countries/in returns 200 with code `IN` / name `India`.
- `testGetCountryException` — GET /countries/az returns 404 with reason `Country not found`.

```bash
mvn clean test
```
