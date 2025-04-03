package com.example.location.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String city;

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;

    public Location(String city, Double latitude, Double longitude) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}