package com.projetofinal5.ProjetoFinal5.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepResultDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
