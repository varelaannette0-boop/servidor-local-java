
////class Pessoa {
//    String nome;
//    String morada;
//    int idade;
//
//    //public Pessoa(String novoNome, String novaMorada, int novaIdade ) {
//        this.nome = novoNome;
//        this.morada = novaMorada;
//        this.idade = novaIdade;
//
//    }
//}
//
////public class Main {
//    public static void main(String[] args) {
//
//        Pessoa pessoa1 = new Pessoa("Cristalina", "Praia", 27);
//
//        System.out.println("nome da pessoa1:" + pessoa1.nome);
//        System.out.println("morada da pessoa1:" + pessoa1.morada);
//        System.out.println("idade da pessoa1:" + pessoa1.idade);
//
//
//        System.out.println("Hello World");
//
//        String nome = "Cristalina";
//        String morada = "Praia";
//        Integer idade = 26;
//
////        System.out.println("Nome: " + nome);
////        System.out.println("Morada: " + morada);
////        System.out.println("Idade: " + idade);
//    }
//
//
//}

public class Main {

    public static void main(String[] args) {


        Pessoa cliente = new Pessoa("Cristal", 25, 2000.0);


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


        System.out.println("========== CATÁLOGO DO MARKETPLACE ==========");

        System.out.println("\nServiço 1");
        System.out.println("Título: " + servico1.titulo);
        System.out.println("Descrição: " + servico1.descricao);
        System.out.println("Preço: " + servico1.preco + " CVE");
        System.out.println("Ativo: " + servico1.estaAtivo);

        System.out.println("\nServiço 2");
        System.out.println("Título: " + servico2.titulo);
        System.out.println("Descrição: " + servico2.descricao);
        System.out.println("Preço: " + servico2.preco + " CVE");
        System.out.println("Ativo: " + servico2.estaAtivo);


        System.out.println("\n========== DESCONTO ==========");
        servico2.aplicarDesconto(10);


        System.out.println("\n========== DISPONIBILIDADE ==========");
        servico2.verificarDisponibilidade();


        System.out.println("\n========== COMPRAS ==========");

        cliente.comprarServico(servico1);
        cliente.comprarServico(servico2);


        System.out.println("\n========== RESUMO ==========");
        System.out.println("Cliente: " + cliente.nome);
        System.out.println("Saldo restante: " + cliente.saldo + " CVE");


    }
}

class Pessoa {

    String nome;
    int idade;
    Double saldo;

    public Pessoa(String nome, int idade, Double saldo) {
        this.nome = nome;
        this.idade = idade;
        this.saldo = saldo;
    }
    public void comprarServico(Servico servicoEscolhido) {
        if (this.saldo > servicoEscolhido.preco && servicoEscolhido.estaAtivo == true) {
            this.saldo = this.saldo - servicoEscolhido.preco;

            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Erro: saldo insuficiente ou servico inativo.");
        }
    }
}


class Servico {

    String titulo;
    String descricao;
    double preco;
    boolean estaAtivo;

    public Servico(String titulo, String descricao, double preco, boolean estaAtivo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.estaAtivo = estaAtivo;
    }

    public  void aplicarDesconto(double percentagem) {
        double valorDesconto = (this.preco * percentagem) / 100;

        this.preco = this.preco - valorDesconto;

        System.out.println("Desconto Aplicado com sucesso!");
        System.out.println("valor final: " + this.preco);
    }

    public void verificarDisponibilidade() {
        if (this.estaAtivo) {
            System.out.println("Servico " + this.titulo + " esta disponivel!");
        } else {
            System.out.println("Servico " + this.titulo + " nao esta disponivel!");
        }
    }


}