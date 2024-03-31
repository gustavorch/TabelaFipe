package br.com.alura.TabelaFipe.principal;

import java.util.Comparator;
import java.util.Scanner;

import br.com.alura.TabelaFipe.model.Dados;
import br.com.alura.TabelaFipe.model.Modelos;
import br.com.alura.TabelaFipe.service.ConsumoApi;
import br.com.alura.TabelaFipe.service.ConverteDados;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
                ***OPÇÕES***
                Carro
                Moto
                Caminhão

                Digite uma das opções para consultar:
                """;
        System.out.println(menu);
        var opcao = scanner.nextLine();

        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas/";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas/";
        } else {
            endereco = URL_BASE + "caminhoes/marcas/";
        }

        var json = consumo.obterDados(endereco);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                // ordena do menor código ao maior
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código da marca que deseja consultar: ");
        var codigoMarca = scanner.nextLine();

        endereco = endereco + codigoMarca + "/modelos/";
        json = consumo.obterDados(endereco);

        // O modelo já está representado como uma lista, por isso é utilizado
        // .obterDados ao invés de .obterLista
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos da marca selecionada: ");
        modeloLista.modelos().stream()
                // também pode ser utilizado Dados::nome para ordenar por ordem alfabética.
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

    }
}
