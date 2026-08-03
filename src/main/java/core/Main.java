package core;

import model.Pessoa;
import model.Servico;
import utils.Formatador;
import utils.ExportadorDados;


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




    }
}










