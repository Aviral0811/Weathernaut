# Weather Forecast RESTful APIs using Spring Boot

This project demonstrates how to build a Spring Boot application that integrates with the Weather API from RapidAPI. It exposes RESTful APIs to retrieve weather forecast information for any city. The APIs require header-based authentication using a random client ID and client secret.
Edit: Updated to use JWT Token based Authentication

## Getting Started

Follow these steps to set up and run the project locally:

1. Clone the repository:

   ```bash
   git clone https://github.com/Aviral0811/Weathernaut.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Weathernaut
   ```

3. Open the `src/main/resources/application.properties` file and replace `<your_rapidapi_key>` with your actual RapidAPI key.

4. Build the project using Maven:

   ```bash
   mvn clean install
   ```

5. Run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

The application will start, and you can access the APIs using the provided endpoints.

## API Endpoints

### API 1: Get Weather Forecast Summary by City

Get a summary of the weather forecast for a specific city.

- **Endpoint:** `GET v1/weather/forecast/{location}`
- **Headers:**
  - `client_id`: client
  - `client_secret`: QgdVGmED1hkaS8xd6qnOPqv7ko5H6rlw
- **Example Request:**
  ```bash
  curl -H "ClientId: <your_client_id>" -H "ClientSecret: <your_client_secret>" http://localhost:8080/weather/forecast-summary/NewYork
  ```
- **Example Response:**
  ```json
  {
    "city": "New York",
    "forecastSummary": "Partly cloudy with chance of showers",
    "temperature": "24°C"
  }
  ```

### API 2: Get Hourly Weather Forecast by City

Get hourly weather forecast details for a specific city.

- **Endpoint:** `GET v1/weather/hourly/{location}`
- **Headers:**
  - `client_id`: client
  - `client_secret`: QgdVGmED1hkaS8xd6qnOPqv7ko5H6rlw
- **Example Request:**
  ```bash
  curl -H "ClientId: <your_client_id>" -H "ClientSecret: <your_client_secret>" http://localhost:8080/weather/hourly-forecast/London
  ```
- **Example Response:**
  ```json
  {
    "city": "London",
    "hourlyForecast": [
      {
        "time": "12:00 PM",
        "temperature": "20°C",
        "conditions": "Partly cloudy"
      },
      {
        "time": "1:00 PM",
        "temperature": "21°C",
        "conditions": "Partly cloudy"
      },
      // ... more hourly forecast data
    ]
  }
  ```

## Authentication

Both APIs require header-based authentication using the `ClientId` and `ClientSecret` headers. You should take random client IDs and client secrets for weather request and include them in the headers in HttpUtils.java file as hard code.

## API Documentation

For more details about the underlying APIs used in this project, refer to the [RapidAPI Forecast9 documentation](https://rapidapi.com/wettercom-wettercom-default/api/forecast9).

Feel free to explore and enhance the project as needed!
