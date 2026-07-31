package core;

import model.Pessoa;
import model.Servico;
import model.Vendedor;

public class Main {

    public static void main(String[] args) {


        Pessoa cliente = new Pessoa("Cristal","Achada Grande Trás", 20, 2000.0 );

        Servico web = new Servico("Website", "Site corporativo", 1500.0, true);

        try {
            cliente.comprarServico(web);
        }

        catch (Exception erro) {
            System.out.println("ALERTA NO SISTEMA: " + erro.getMessage());
        }

        cliente.mostrarHistorico();


        Servico servico1 = new Servico(
                "Formação em Java",
                "Curso básico de programação Java",
                49.99,
                true
        );

        Servico servico2 = new Servico(
                "Criação de Logótipo",
                "Design de logótipo profissional",
                75.00,
                true
        );

        Vendedor vendedor = new Vendedor("Maria", "Fogo", 53, 4000.0, 100.00, 9712257);

        Servico servico = new Servico("Curso de Formação de Estética", "Curso competo de estética", 25.000, true);

        Servico servicoErro = new Servico(
                "Teste de erro",
                "Serviço com preço inválido",
                0,
                true
        );

        try {
            vendedor.publicarServico(servicoErro);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }



        System.out.println("========== CATÁLOGO DO MARKETPLACE ==========");

        System.out.println("\nServiço 1");
        System.out.println("Título: " + servico1.getTitulo());
        System.out.println("Descrição: " + servico1.getDescricao());
        System.out.println("Preço: " + servico1.getPreco() + " CVE");
        System.out.println("Ativo: " + servico1.getEstaAtivo());

        System.out.println("\nServiço 2");
        System.out.println("Título: " + servico2.getTitulo());
        System.out.println("Descrição: " + servico2.getDescricao());
        System.out.println("Preço: " + servico2.getPreco() + " CVE");
        System.out.println("Ativo: " + servico2.getEstaAtivo());


        System.out.println("\n========== DESCONTO ==========");
        servico2.aplicarDesconto(10);


        System.out.println("\n========== DISPONIBILIDADE ==========");
        servico2.verificarDisponibilidade();




        System.out.println("=== Dados do Vendedor ===");
        System.out.println("Nome: " + vendedor.getNome());
        System.out.println("Morada: " + vendedor.getMorada());
        System.out.println("Idade: " + vendedor.getIdade());
        System.out.println("Saldo: " + vendedor.getSaldo());
        System.out.println("Taxa de comissão: " + vendedor.getTaxaComissao() + "%");



        try {
            vendedor.comprarServico(servico);
            cliente.comprarServico(servico1);
            cliente.comprarServico(servico2);
        } catch (Exception erro) {
            System.out.println("Alerta No Sistema: + erro.getMessage()");
        }





        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Morada: " + cliente.getMorada());
        System.out.println("Idade: " + cliente.getIdade());
        System.out.println("Saldo restante: " + cliente.getSaldo() + " CVE");


    }
}










