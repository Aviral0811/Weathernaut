package com.nexus.skyscan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherData {
    @JsonProperty("location")
    private Map<String, Object> location;

    @JsonProperty("forecast")
    private Map<String, Object> forecast;
}
