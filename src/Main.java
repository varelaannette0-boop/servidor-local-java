
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
 class Servico {
    String titulo;
    String descricao;
    Double preco;
    Boolean estaAtivo;

    public Servico(String titulo, String descricao, Double preco, Boolean estaAtivo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.estaAtivo = estaAtivo;

    }
}
public class Main {
    public static void main(String[] args) {

        Servico servico1 = new Servico(
                "Formação em Java",
                "Curso básico de formação em Java",
                10.000,
                true
        );

        Servico servico2 = new Servico(
                "Criação de Logótipo",
                "Design de logótipo profissional",
                15.000,
                true
        );

public class Main {
    public static void main(String[] args) {
        Pessoa cliente = new Pessoa(Annette, 26);

    }
}
        public class Pessoa {
     String nome;
     int idade;

     public Pessoa(String nome, int idade) {
         this.nome = nome;
         this.idade = idade;
     }
 }

    System.out.println("===== Catálogo Marketplace =====");
    System.out.println("Servico 1:");
    System.out.println("Titulo: " + servico1.titulo);
    System.out.println("Descricao: " + servico1.descricao);
    System.out.println("Preco: " + servico1.preco + " CVE");
    System.out.println("Ativo: " + servico1.estaAtivo);

    System.out.println("-----------------------------");

    System.out.println("Servico 2:");
    System.out.println("Titulo: " + servico2.titulo);
    System.out.println("Descricao: " + servico2.descricao);
    System.out.println("Preco: " + servico2.preco + " CVE");
    System.out.println("Ativo: " + servico2.estaAtivo);

    System.out.println("\n==== Compra Realizada ====");
    System.out.println(cliente.nome + " comprou o serviço \"" + servico1.titulo + "\" por " + servico1.preco + "CVE.");




};


}