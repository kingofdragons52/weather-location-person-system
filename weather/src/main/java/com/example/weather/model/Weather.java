package com.example.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Weather {
    @Id
    @GeneratedValue
    private Long id;

    private String city;

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;

    @NonNull
    private String description;

    @NonNull
    private Double temperature;

    public Weather(String city, Double latitude, Double longitude, String description, Double temperature) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.temperature = temperature;
    }
}