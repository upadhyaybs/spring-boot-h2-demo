# Restaurant API
This is a RESTful API for managing restaurant information. The API provides endpoints for creating, retrieving, updating, and deleting restaurant data.

## Features
- Save a new restaurant

- List all restaurants

- Find a restaurant by ID

- Find restaurants by name

- Find restaurants by city

- Find restaurants by zip code

- Find restaurants by state

## Technologies
- Java 21

- Spring Boot 3.x

- H2 Database (in-memory)

- Swagger UI

## Getting Started
### Prerequisites
- Java 17 or higher

- Maven

### Installation
1. Clone the repository:
```
git clone https://github.com/your-username/restaurant-api.git
```
2. Navigate to the project directory:
```
cd springboot-h2-example
```
3. Build the project:
```
mvn clean install
```

### Running the Application

1. Start the application:
```
mvn spring-boot:run
```

The application will start running on
http://localhost:8080

2. Access the Swagger UI:
````
http://localhost:8080/swagger-ui.html
````

The Swagger UI provides documentation and a user interface for testing the API endpoints.

## API Endpoints
The API endpoints are documented in the Swagger UI. Here's a brief overview:

- PUT /restaurants
: Save a new restaurant

- GET /restaurants
: List all restaurants

- GET /restaurants/id/{id}
: Find a restaurant by ID

- POST /restaurants/name
: Find restaurants by name

POST /restaurants/city
: Find restaurants by city

- GET /restaurants/zip/{zipCode}
: Find restaurants by zip code

- GET /restaurants/state/{state}
: Find restaurants by state

Contributing
Contributions are welcome! Please follow the standard GitHub workflow:

1. Fork the repository

2. Create a new branch

3. Make your changes

4. Commit and push your changes

5. Create a pull request

## License

## Contact
