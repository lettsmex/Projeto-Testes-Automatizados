package com.projetofinal5.ProjetoFinal5.dto;

import lombok.Data;

@Data
public class WeatherResponse {
    private Main main;
    private String name;

    @Data
    public static class Main {
        private double temp;
    }
}