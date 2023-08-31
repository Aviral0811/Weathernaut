package com.nexus.skyscan.controller;


import com.nexus.skyscan.model.WeatherData;
import com.nexus.skyscan.service.WeatherDataHandler;
import com.nexus.skyscan.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/weather")
@Slf4j
public class WeatherController {

    @Autowired
    WeatherDataHandler weatherDataHandler;
    @GetMapping("/forecast/{location}")
    public ResponseEntity<WeatherData> forecastSummary(@PathVariable String location,
                                                       @RequestHeader("client_id") String clientId,
                                                       @RequestHeader("client_secret") String clientSecret) {
        WeatherData weatherData = null;
        HttpStatus status = HttpStatus.OK;
        try {
            log.info("Request received for location: {}", location);
            HttpUtils.validateHeaders(clientId, clientSecret);
            log.info("Request received for location: {}", location);
            weatherData = weatherDataHandler.fetchForecastSummary(location);
        }  catch (Exception e) {
            log.error("Error while fetching weather data for location: {}, Stacktrace : {}", location, e.getStackTrace());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(weatherData, status);
    }

    @GetMapping("/hourly/{location}")
    public ResponseEntity<WeatherData> hourlyForecastSummary(@PathVariable String location,
                                                             @RequestHeader("client_id") String clientId,
                                                             @RequestHeader("client_secret") String clientSecret) {
        WeatherData weatherData = null;
        HttpStatus status = HttpStatus.OK;
        try {
            HttpUtils.validateHeaders(clientId, clientSecret);
            log.info("Request received for location: {}", location);
            weatherData = weatherDataHandler.fetchHourlyForecastSummary(location);
        }  catch (Exception e) {
            log.error("Error while fetching weather data for location: {}, Stacktrace : {}", location, e.getStackTrace());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(weatherData, status);
    }
}