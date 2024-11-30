package com.projetofinal5.ProjetoFinal5.controller;

import com.projetofinal5.ProjetoFinal5.dto.WeatherResponse;
import com.projetofinal5.ProjetoFinal5.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String city) {
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        return ResponseEntity.ok(weatherResponse);
    }
}