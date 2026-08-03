package utils;

import com.google.gson.Gson;
import model.Pessoa;

public class ExportadorDados {

    public static void exportarCarrinhoParaJson(Pessoa cliente) {
        Gson tradutor = new Gson();
        String json = tradutor.toJson(cliente.getCarrinhoDeCompras());
        System.out.println(json);

    }
}
