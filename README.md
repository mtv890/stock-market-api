# Super Simple Stock Market API

## Overview
Super Simple Stock Market API is a Spring Boot application designed to manage trades and stocks for the Global Beverage Corporation Exchange. 
It provides functionality to calculate stock metrics, record trades, and manage stock data.

## Features
- **Stock Operations**:
    - Calculate dividend yield for a given stock and price.
    - Calculate P/E Ratio for a given stock and price.
    - Record trades with timestamp, quantity, buy/sell indicator, and price.
    - Calculate Volume Weighted Stock Price based on trades in the past 5 minutes.
    - Calculate GBCE All Share Index using the geometric mean of Volume Weighted Stock Prices for all stocks.

- **Security**:
    - Stateless authentication using JWT tokens.
    - Public endpoints for Swagger UI and API documentation.
    - Protected `/api/**` endpoints requiring valid JWT tokens.

- **Rate limit**:
    - Implemented Rate limit per user using AOP.

- **Messaging**:
    - RabbitMQ integration for managing pending trades using queues, exchanges, and routing keys.

## Technologies Used
- **Programming Language**: Java 17
- **Frameworks**: Spring Boot, Spring Security
- **Build Tool**: Maven
- **Messaging**: RabbitMQ
- **Testing**: JUnit, Hamcrest

## Project Structure
- `src/main/java/com/shilton/stockmarketapi/config`: Contains configuration classes for security and RabbitMQ.
- `src/main/java/com/shilton/stockmarketapi/service`: Contains service classes for business logic.
- `src/main/java/com/shilton/stockmarketapi/repository`: Contains repository interfaces for data access.
- `src/main/java/com/shilton/stockmarketapi/domain`: Contains domain models for stocks and trades.

## Getting Started
### Prerequisites
- Java 17
- Maven
- Docker (optional for RabbitMQ setup)

### Build & Run
1. Clone the repository:
   ```bash
   git clone https://github.com/mtv890/stock-market-api.git
   cd stock-market-api

2. Build the project:
   ```bash
   mvn clean install
   ```
3. Navigate to http://localhost:8080/swagger-ui.html to access the Swagger UI for API documentation.

2. Some endpoints require Authentication, follow these Steps:
 - Navigate to https://developers.google.com/oauthplayground/
 - Step 1: Select Google OAuth API v2 and select https://www.googleapis.com/auth/userinfo.email -> Authorize APIs
 - Step 2: Select Exchange auth code for tokens get "id_token" from response.
 - Step 3: Use the id_token as Bearer token in the Authorization header for API requests (Postman).