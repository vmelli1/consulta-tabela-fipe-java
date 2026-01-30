package br.com.projetofinal.Projeto.fipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResponse(List<ItemFipe> modelos) {

}
