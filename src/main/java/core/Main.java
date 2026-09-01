package core;

import com.google.gson.Gson;
import model.Pessoa;
import model.ProdutoExterno;
import model.Servico;
import utils.ExportadorDados;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {

    public static void main(String[] args) {

        Pessoa cliente = new Pessoa(
                "Cristal",
                "Achada Grande Trás",
                20,
                80000.0
        );


        Servico servico1 = new Servico(
                "Website Corporativo",
                "Criação de website profissional",
                25000.0,
                true
        );

        Servico servico2 = new Servico(
                "Design de Logótipo",
                "Criação de identidade visual",
                5000.0,
                true
        );

        Servico servico3 = new Servico(
                "Marketing Digital",
                "Gestao de redes sociais",
                5000.0,
                true
        );

        try {
            cliente.comprarServico(servico1);
            cliente.comprarServico(servico2);
            cliente.comprarServico(servico3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ExportadorDados exportador = new ExportadorDados();
        exportador.exportarCarrinhoParaJson(cliente);


        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest pedido = HttpRequest.newBuilder()
                    .uri(URI.create("https://dummyjson.com/products/1"))
                    .GET()
                    .build();

            HttpResponse<String> resposta = client.send(pedido, HttpResponse.BodyHandlers.ofString());

            Gson tradutor = new Gson();
            ProdutoExterno produto = tradutor.fromJson(resposta.body(), ProdutoExterno.class);


            System.out.println("Codigo de resposta " + resposta.statusCode());
            System.out.println("Dados de resposta: " + resposta.body());

            System.out.println(
                    "O produto importado é o "
                            + produto.getTitle()
                            + " e custa "
                            + produto.getPrice()
                            + " dólares"
            );

        } catch(Exception e) {
            System.out.println("erro ao chamar api:" + e.getMessage());
        }


    }
    }





















