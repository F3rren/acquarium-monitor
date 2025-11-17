# Acquarium Monitor

![Java](https://img.shields.io/badge/java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.1-green.svg)
![Status: Stable](https://img.shields.io/badge/status-stable-success.svg)
![License: MIT](https://img.shields.io/badge/license-MIT-green.svg)

A RESTful backend for aquarium monitoring, providing endpoints to manage tanks, measurements, tasks, and inhabitants. Built with Java 21, Spring Boot, Hibernate, and PostgreSQL.

## Current Status

- All endpoints are stable and fully operational; no mock data included.
- No user authentication implemented (intended for use by up to two users).
- Data stored and retrieved exclusively from PostgreSQL in JSON format.

## Features

- **Aquarium Management:** Create, read, update, and delete tanks.
- **Parameter Tracking:** Save and retrieve water values; access historical data with flexible queries.
- **Maintenance Tasks:** CRUD operations for aquarium tasks, completion tracking.
- **Livestock Management:** CRUD for inhabitants (fish, corals) associated with each tank.
- **Species Database:** Query comprehensive lists and details for fish and corals.

## Endpoints (Base URL: `/api/v1/`)

Aquariums
GET /aquariums
POST /aquariums
GET /aquariums/{id}
PUT /aquariums/{id}
DELETE /aquariums/{id}

Parameters
GET /aquariums/{id}/parameters
POST /aquariums/{id}/parameters
GET /aquariums/{id}/parameters/history?from=&to=&param=

Tasks
GET /aquariums/{id}/tasks
POST /aquariums/{id}/tasks
PUT /aquariums/{id}/tasks/{taskId}
DELETE /aquariums/{id}/tasks/{taskId}
POST /aquariums/{id}/tasks/{taskId}/complete

Inhabitants
GET /aquariums/{id}/inhabitants
POST /aquariums/{id}/inhabitants
PUT /aquariums/{id}/inhabitants/{inhabitantId}
DELETE /aquariums/{id}/inhabitants/{inhabitantId}

Species Database
GET /species/fish
GET /species/fish/{id}
GET /species/corals
GET /species/corals/{id}


## Getting Started

1. Clone the repository:
git clone https://github.com/F3rren/acquarium-monitor.git
cd acquarium-monitor

2. Setup PostgreSQL and configure `application.properties`.
3. Build and run the Spring Boot application.

## License

This project is licensed under the MIT License.
