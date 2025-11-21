# Aquarium Monitor

![Java 21](https://img.shields.io/badge/java-21-blue.svg)
![Spring Boot 3.1](https://img.shields.io/badge/spring--boot-3.1-green.svg)
![Dockerized](https://img.shields.io/badge/docker-ready-blue.svg)
![CI/CD](https://img.shields.io/badge/CI--CD-GitHub%20Actions-blue.svg)
![MIT License](https://img.shields.io/badge/license-MIT-green.svg)

A **RESTful backend** for comprehensive aquarium monitoring—manage tanks, water parameters, maintenance tasks, and inhabitant records.  
**Built with Java 21, Spring Boot, Hibernate, PostgreSQL. Fully dockerized, production-ready, and equipped with automated CI/CD workflows.**

---

> **Note:** This backend is designed to work alongside the [Aquarium Interface](https://github.com/F3rren/Aquarium-interface) frontend.  
> *It is not intended as a public/general-purpose open API.*

---

## Project Status

- **Stable:** All core endpoints verified and production-tested
- **Data Layer:** PostgreSQL (internal JSON structure)
- **Single-user/private use:** No user authentication by default
- **CI/CD:** Automated via GitHub Actions for build, test, and deploy processes
- **Stack:** Java 21, Spring Boot 3.1, Docker, PostgreSQL

---

## Key Features

- **Aquarium Management:** CRUD operations on tanks (name, type, volume, etc.)
- **Parameter Tracking:** Log, query, and analyze water parameters (temperature, pH, salinity, etc.)
- **Maintenance:** Task scheduling, completion tracking, historical logs
- **Inhabitants Database:** Add/edit aquatic species (fish, corals) by tank
- **Species Reference:** Built-in species database for ease of logging and selection
- **Clean architecture:** Clear separation of controller/service/repository layers
- **Monitoring:** Exposed health endpoints with Spring Boot Actuator

---

## Architecture, Dockerization & CI/CD

- **Fully dockerized:** 
  - Includes a production-grade `Dockerfile` and Docker Compose configs for both development and production setups
  - Data persistence ensured via Docker volumes, isolated environments for development/production
- **Environment variables:** 
  - Configuration via `.env` file (see provided `.env.example`)
  - Keeps credentials/secrets out of source
- **CI/CD pipeline:** 
  - Automated builds and tests run on every push or pull request using GitHub Actions
  - Safe merge enforcement and artifact publishing
  - Future extensibility for lint, coverage, and deploy actions

---

## Quick Start

1. **Clone the repository**
    ```
    git clone https://github.com/F3rren/aquarium-monitor.git
    cd aquarium-monitor
    ```

2. **Prepare environment variables**
    - Copy `.env.example` to `.env` and edit as needed

3. **Run with Docker Compose (recommended for development)**
    ```
    docker compose -f docker-compose.dev.yml up
    ```
    - API available at: http://localhost:8080/api

4. **(Alternative) Local build without Docker**
    - Set up PostgreSQL and configure `src/main/resources/application.properties`
    - Run:
      ```
      ./mvnw clean package
      java -jar target/*.jar
      ```

---

## Main API Endpoints

| Area              | Method & Path                                      |
|-------------------|----------------------------------------------------|
| Aquariums         | GET/POST `/aquariums`, GET/PUT/DELETE `/aquariums/{id}`    |
| Water Parameters  | GET/POST `/aquariums/{id}/parameters`, GET `/aquariums/{id}/parameters/history?from=...` |
| Maintenance Tasks | GET/POST `/aquariums/{id}/tasks`, PUT/DELETE `/aquariums/{id}/tasks/{taskId}`          |
| Inhabitants       | GET/POST `/aquariums/{id}/inhabitants`                  |
| Species           | GET `/species/fish`, GET `/species/corals`          |

## API Documentation

[![OpenAPI Spec](https://img.shields.io/badge/OpenAPI-3.0-green)](./docs/openapi.json)

The complete OpenAPI 3.0 specification is in [`./docs/openapi.json`](./docs/openapi.json).  
- **Live Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Raw OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Use this file to:
- Edit or visualize it with [Swagger Editor](https://editor.swagger.io/)
- Import into Postman/Insomnia for live API testing
- Generate backend/frontend clients using [OpenAPI Generator](https://openapi-generator.tech/)

**How to update:**  
After backend changes, go to [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs), save the result as `docs/openapi.json` and commit it to the repository.

---

## Example Request Bodies

**Creating a new aquarium:**
```
{
  "name": "My Reef Tank",
  "volume": 150,
  "type": "Marine"
}
```
**Adding a new parameter:**
```
{
  "temperature": 20.0,
  "ph": 6.23,
  "salinity": 1020,
  "orp": 355
}
```

---


## Roadmap

- [ ] Advanced CI/CD: add coverage, lint, e2e tests
- [ ] Automated cloud/database backup
- [ ] Authentication & multi-user profile support
- [ ] Export/import tools for JSON/CSV data
- [ ] Enhanced error handling & validation
- [ ] Automated API tests & integration with frontend

---

## License

MIT © F3rren
