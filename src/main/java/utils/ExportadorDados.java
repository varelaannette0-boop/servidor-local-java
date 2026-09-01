package utils;

import com.google.gson.Gson;
import model.Pessoa;
import model.ProdutoExterno;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExportadorDados {

    public static void exportarCarrinhoParaJson(Pessoa cliente) {

        Gson tradutor = new Gson();
        String json = tradutor.toJson(cliente.getCarrinhoDeCompras());


        System.out.println(json);

    }
}
