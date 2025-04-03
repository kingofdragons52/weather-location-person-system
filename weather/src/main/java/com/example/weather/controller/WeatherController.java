package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherRepository repository;

    @GetMapping("/coordinates")
    public ResponseEntity<Weather> getWeatherByCoordinates(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        Weather weather = repository.findByLatitudeAndLongitude(latitude, longitude);
        if (weather != null) {
            return ResponseEntity.ok(weather);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Weather createWeather(@RequestBody Weather weather) {
        return repository.save(weather);
    }
}