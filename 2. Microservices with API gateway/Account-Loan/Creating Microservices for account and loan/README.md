# Bank Microservices — Account & Loan (Spring Boot 3 / Spring Cloud)

This is the "Creating Microservices for account and loan" exercise from the
PDF, updated for **Spring Boot 3.2.5** and **Java 17** (Spring Boot 2.5.5,
used in the original PDF, is end-of-life and doesn't support Java 17+, which
current Spring Boot 3 requires).

## Structure

```
microservices/
├── account/   -> Account microservice (port 8080)
└── loan/      -> Loan microservice (port 8081)
```

Both are already wired for the next step in the PDF (Eureka registration),
via the `spring-cloud-starter-netflix-eureka-client` dependency and the
`eureka.client.service-url.defaultZone` property. If you haven't built the
Eureka Discovery Server yet, you can safely ignore the eureka lines — the
services will still start and serve requests; they'll just log connection
warnings while eureka-server isn't running.

## Prerequisites

- JDK 17+
- Maven 3.6+ (or use the included `mvnw` if you generate one via
  `mvn -N io.takari:maven:wrapper`)

## Build & Run — Account Microservice

```bash
cd /account
mvn clean package
mvn spring-boot:run
```

Test it:
```
GET http://localhost:8080/accounts/00987987973432
```
Response:
```json
{ "number": "00987987973432", "type": "savings", "balance": 234343 }
```

## Build & Run — Loan Microservice

```bash
cd /loan
mvn clean package
mvn spring-boot:run
```

Test it:
```
GET http://localhost:8081/loans/H00987987972342
```
Response:
```json
{ "number": "H00987987972342", "type": "car", "loan": 400000, "emi": 3258, "tenure": 18 }
```

## Notes

- Run both services at the same time from two separate terminals — each
  binds to its own port (8080 and 8081), so there's no conflict.
- `@EnableDiscoveryClient` is included per the PDF's instructions, though
  in Spring Boot 3 / Spring Cloud 2023.x it's auto-configured by the
  presence of the eureka-client dependency and is technically optional.
- Next steps per the PDF: build the Eureka Discovery Server, then the
  API Gateway with a global logging filter — say the word and I'll
  generate those two projects the same way.
