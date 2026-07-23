# spring-rest-handson — REST - Country Web Service

## Objective
Write REST services that return India's country details, and the full list of countries,
in the earlier created spring-learn application (Hello World hands-on).

## Endpoints

### GET /country
| | |
|---|---|
| Controller | `com.cognizant.springlearn.controller.CountryController` |
| Method | `getCountryIndia()` |
| Implementation | Loads the `india` bean from Spring XML config (`india-bean.xml`) and returns it |

Sample response:
```json
{
  "code": "IN",
  "name": "India"
}
```

### GET /countries
| | |
|---|---|
| Controller | `com.cognizant.springlearn.controller.CountryController` |
| Method | `@GetMapping("/countries") getAllCountries()` |
| Implementation | Loads the `countryList` bean from `country.xml` and returns it |

Sample response:
```json
[
  { "code": "IN", "name": "India"},
  { "code": "US", "name": "United States"},
  { "code": "JP", "name": "Japan"},
  { "code": "DE", "name": "Germany"}
]
```

## Run
```bash
mvn spring-boot:run
```
App runs on port `8083`.

## Try it
- http://localhost:8083/country
- http://localhost:8083/countries

Inspect response headers in the browser Network tab (F12) or Postman's "Headers" tab, and
review how the `Country` bean is serialized to JSON.

## Test
```bash
mvn clean test
```
