package com.example.person.service;

import com.example.person.dto.PersonWeatherDTO;
import com.example.person.dto.LocationResponse;
import com.example.person.dto.WeatherResponse;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherIntegrationService {

    @Autowired
    private PersonRepository personRepository;

    private final WebClient webClient;

    @Value("${location.service.url}")
    private String locationServiceUrl;

    @Value("${weather.service.url}")
    private String weatherServiceUrl;

    public WeatherIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<PersonWeatherDTO> getWeatherByPersonId(int personId) {
        return personRepository.findById(personId)
                .map(person -> {
                    // Запрос к location-service
                    Mono<LocationResponse> locationMono = webClient.get()
                            .uri(locationServiceUrl + "/locations/city/{city}", person.getLocation())
                            .retrieve()
                            .bodyToMono(LocationResponse.class);  // Используем LocationResponse

                    // Запрос к weather-service
                    return locationMono.flatMap(location -> {
                        return webClient.get()
                                .uri(weatherServiceUrl + "/weather/coordinates?latitude={lat}&longitude={lon}",
                                        location.getLatitude(), location.getLongitude())
                                .retrieve()
                                .bodyToMono(WeatherResponse.class)  // Используем WeatherResponse
                                .map(weather -> new PersonWeatherDTO(
                                        person.getId(),
                                        person.getName(),
                                        person.getLocation(),
                                        weather.getDescription(),
                                        weather.getTemperature()
                                ));
                    });
                })
                .orElse(Mono.error(new RuntimeException("Person not found")));
    }
}