package br.com.projetofinal.Projeto.fipe.principal;


import br.com.projetofinal.Projeto.fipe.model.ItemFipe;
import br.com.projetofinal.Projeto.fipe.model.ModelosResponse;
import br.com.projetofinal.Projeto.fipe.model.VeiculoFipe;
import br.com.projetofinal.Projeto.fipe.service.ConsumeApi;
import br.com.projetofinal.Projeto.fipe.service.ConversionData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FipeConsoleApp {
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    private ConsumeApi consumeApi = new ConsumeApi();
    private ConversionData conversionData = new ConversionData();
    private Scanner leitura = new Scanner(System.in);

    public void executar(){
        var menu = ("""
                === Consulta Tabela FIPE ===  
                Carro
                Moto
                Caminhão
                
                Digite uma opção:
                """);

        System.out.println(menu);

        var tipoVeiculo  = leitura.nextLine();
        String endereco;
        if(tipoVeiculo .toLowerCase().contains("carr")){
            endereco = URL + "carros/marcas";
        }else if(tipoVeiculo .toLowerCase().contains("mot")){
            endereco = URL + "motos/marcas";
        }else{
            endereco = URL + "caminhoes/marcas";
        }

        var json = consumeApi.obterDados(endereco);
        var marcas  = conversionData.colectDados(json, ItemFipe.class);
        marcas .stream()
                .forEach(System.out::println);

        System.out.println("\nDigite o número da marca desejada: ");
        var codigoMarca  = leitura.nextLine();

        endereco = endereco + "/" +codigoMarca + "/modelos";


        System.out.println("\nModelos disponíveis para a marca escolhida: ");
        json = consumeApi.obterDados(endereco);
        var respostaModelos  = conversionData.obterDados(json, ModelosResponse.class);
        respostaModelos .modelos().stream()
                        .sorted(Comparator.comparing(ItemFipe::codigo))
                         .forEach(System.out::println);

        System.out.println("\nDigite parte do nome do modelo (ex: gol, civic, uno): ");
        var codigoModelo = leitura.nextLine();
        List<ItemFipe> modelos = respostaModelos .modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(codigoModelo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nModelos filtrados");
                 modelos.forEach(System.out::println);



        System.out.println("\nDigite o código do modelo para consultar os valores: ");
        var codModelo = leitura.nextLine();
        endereco = endereco + "/" +codModelo+ "/anos";

        json = consumeApi.obterDados(endereco);
        List<ItemFipe> anos = conversionData.colectDados(json, ItemFipe.class);
        List<VeiculoFipe> veiculos = new ArrayList<>();



        for(int i = 0; i < anos.size(); i++){
            var enderecoAno = endereco + "/" +anos.get(i).codigo();
            json = consumeApi.obterDados(enderecoAno);
            VeiculoFipe veiculo = conversionData.obterDados(json, VeiculoFipe.class);
            veiculos.add(veiculo);
        }

        
        System.out.println("\nVeículos encontrados para os critérios informados: ");
        veiculos.forEach(System.out::println);

        System.out.println("\nDigite o ano desejado para filtrar os veículos: ");
        int anoFiltro = Integer.parseInt(leitura.nextLine());
        veiculos.stream()
                .filter(veiculo -> veiculo.ano() == anoFiltro)
                .sorted(Comparator.comparing(VeiculoFipe::ano))
                .forEach(System.out::println);





    }

}

