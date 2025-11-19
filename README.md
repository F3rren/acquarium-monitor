# Aquarium Monitor
![Java](https://img.shields.io/badge/java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.1-green.svg)
![Status: Stable](https://img.shields.io/badge/status-stable-success.svg)
![License: MIT](https://img.shields.io/badge/license-MIT-green.svg)

A RESTful backend for aquarium monitoring, providing endpoints to manage tanks, measurements, tasks, and inhabitants. Built with Java 21, Spring Boot, Hibernate, and PostgreSQL.

> **Note:**  
> This backend is designed specifically for use with [Aquarium Interface](https://github.com/F3rren/Aquarium-interface).  
> It will not operate as a standalone API for other apps out-of-the-box.

---

## Current Status

- All endpoints are stable and fully operational; no mock data included.
- Data stored and retrieved exclusively from PostgreSQL in JSON format.
- No user authentication implemented (intended for use by up to two users).
- Developed and tested on Java 21 & Spring Boot 3.1.

---

## Features

- **Aquarium Management:** Create, read, update, and delete tanks.
- **Parameter Tracking:** Save and retrieve water values; access historical data with flexible queries.
- **Maintenance Tasks:** CRUD operations for aquarium tasks, completion tracking.
- **Livestock Management:** CRUD for inhabitants (fish, corals) per tank.
- **Species Database:** Query comprehensive lists and details for fish and corals.
- **Clean layered architecture:** Controllers, services, and repositories with Hibernate ORM.

---


## Endpoints (Base URL: `/api`)
- **Aquariums**
```
GET /aquariums
POST /aquariums
GET /aquariums/{id}
PUT /aquariums/{id}
DELETE /aquariums/{id}
```
- **Parameters**
```
GET /aquariums/{id}/parameters
POST /aquariums/{id}/parameters
GET /aquariums/{id}/parameters/history?from=&to=&param=
```
- **Tasks**
```
GET /aquariums/{id}/tasks
POST /aquariums/{id}/tasks
PUT /aquariums/{id}/tasks/{taskId}
DELETE /aquariums/{id}/tasks/{taskId}
POST /aquariums/{id}/tasks/{taskId}/complete
```
- **Inhabitants**
```
GET /aquariums/{id}/inhabitants
POST /aquariums/{id}/inhabitants
PUT /aquariums/{id}/inhabitants/{inhabitantId}
DELETE /aquariums/{id}/inhabitants/{inhabitantId}
```
- **Species Database**
```
GET /species/fish
GET /species/fish/{id}
GET /species/corals
GET /species/corals/{id}
```

## Example Request Body

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
  "temperature": 26.0,
  "ph": 8.2,
  "salinity": 1025,
  "measuredAt": "2025-11-18T09:00:00Z"
}
```

---


## Getting Started

1. Clone the repository:
```
git clone https://github.com/F3rren/aquarium-monitor.git
cd aquarium-monitor
```

3. Setup PostgreSQL and configure `application.properties`.
4. Build and run the Spring Boot application. The backend is now available at `http://localhost:8080/api`

---

## Roadmap

- [ ] Integrate with cloud backup or sync solution
- [ ] Add user authentication and profiles
- [ ] Export/import tank and measurement data
- [ ] Add multi-tenant support (multiple users)
- [ ] Improve error handling and validation
- [ ] Create automated tests for endpoints

---
## License

This project is licensed under the MIT License.
