package com.example.person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @Id @GeneratedValue
    private int id;

    @NonNull private String name;
    @NonNull private String location;

    // Добавленные вручную геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Person(@NonNull String name, @NonNull String location) {
        this.name = name;
        this.location = location;
    }
}