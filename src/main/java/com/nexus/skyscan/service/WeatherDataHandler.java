package com.nexus.skyscan.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexus.skyscan.model.WeatherData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class WeatherDataHandler {

    private final ObjectMapper objectMapper;
    @Value("${weather.api.base.url}")
    private String API_BASE_URL;
    @Value("${weather.api.key}")
    private String API_KEY;
    @Value("${weather.api.host}")
    private String API_HOST;

    public WeatherDataHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WeatherData fetchHourlyForecastSummary(String location) throws IOException, InterruptedException {
        String url = API_BASE_URL + location + "/hourly/";
        HttpRequest request = getHttpRequest(url);
        log.info("Hourly Forecast Request: {}", request);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        log.info("Hourly Forecast Response: {}", responseBody);
        if (response.statusCode() == HttpStatus.OK.value()) {
            return objectMapper.readValue(responseBody, WeatherData.class);
        } else {
            log.error("Received non-OK status code: {}", response.statusCode());
            throw new IOException("Failed to fetch hourly summary");
        }
    }
    public WeatherData fetchForecastSummary(String location) throws IOException, InterruptedException {
        String url = API_BASE_URL + location + "/summary/";
        HttpRequest request = getHttpRequest(url);
        log.info("Forecast Summary Request: {}", request);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        log.info("Forecast Summary Response: {}", responseBody);
        if (response.statusCode() == HttpStatus.OK.value()) {
            return objectMapper.readValue(responseBody, WeatherData.class);
        } else {
            log.error("Received non-OK status code: {}", response.statusCode());
            throw new IOException("Failed to fetch forecast summary");
        }
    }
    private HttpRequest getHttpRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
}