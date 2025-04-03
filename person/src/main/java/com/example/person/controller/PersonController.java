package com.example.person.controller;

import com.example.person.dto.PersonWeatherDTO;
import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import com.example.person.service.WeatherIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private WeatherIntegrationService weatherIntegrationService;

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        if (person.getId() > 0 && repository.existsById(person.getId())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(repository.findById(person.getId()).get());
        }

        Person savedPerson = repository.save(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPerson);
    }

    @GetMapping("/{id}/weather")
    public Mono<PersonWeatherDTO> getWeatherForPerson(@PathVariable int id) {
        return weatherIntegrationService.getWeatherByPersonId(id);
    }
}