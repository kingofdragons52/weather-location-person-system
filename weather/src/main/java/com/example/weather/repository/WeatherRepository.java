package com.example.weather.repository;

import com.example.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findByLatitudeAndLongitude(Double latitude, Double longitude);
}