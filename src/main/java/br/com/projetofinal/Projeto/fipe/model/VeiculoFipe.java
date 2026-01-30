package br.com.projetofinal.Projeto.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoFipe(@JsonAlias("Marca") String marca,
                          @JsonAlias("Modelo") String modelo ,
                          @JsonAlias("AnoModelo") Integer ano,
                          @JsonAlias("Valor") String valor,
                          @JsonAlias("Combustivel") String tipoDeCombustivel) {


    @Override
    public String toString() {
        return String.format("%s %s  ano: %s valor: %s combustível: %s",
                marca, modelo, ano, valor, tipoDeCombustivel);
    }
}
