package br.com.projetofinal.Projeto.fipe.service;

import java.util.List;

public interface IconversionDados {

        <T> T obterDados(String Json, Class<T> classe);
        <T> List<T> colectDados(String Json, Class<T> classe);


}
