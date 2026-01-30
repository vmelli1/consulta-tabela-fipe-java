package br.com.projetofinal.Projeto.fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemFipe(String codigo, String nome ) {
}
