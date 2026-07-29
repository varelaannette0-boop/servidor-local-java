
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


        Pessoa cliente = new Pessoa("Cristal","Achada Grande Trás", 20, 2000.0 );


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



        System.out.println("========== CATÁLOGO DO MARKETPLACE ==========");

        System.out.println("\nServiço 1");
        System.out.println("Título: " + servico1.getTitulo());
        System.out.println("Descrição: " + servico1.getDescricao());
        System.out.println("Preço: " + servico1.preco + " CVE");
        System.out.println("Ativo: " + servico1.estaAtivo);

        System.out.println("\nServiço 2");
        System.out.println("Título: " + servico2.getTitulo());
        System.out.println("Descrição: " + servico2.getDescricao());
        System.out.println("Preço: " + servico2.preco + " CVE");
        System.out.println("Ativo: " + servico2.estaAtivo);


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

        vendedor.comprarServico(servico);

        cliente.comprarServico(servico1);
        cliente.comprarServico(servico2);



        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Morada: " + cliente.getMorada());
        System.out.println("Idade: " + cliente.getIdade());
        System.out.println("Saldo restante: " + cliente.getSaldo() + " CVE");


    }
}

class Pessoa {

    private String nome;
    private String morada;
    private int idade;
    private Double saldo;


    public Pessoa(String novoNome, String novaMorada, int novaIdade, Double novoSaldo) {
        this.nome = novoNome;
        this.morada = novaMorada;
        this.idade = novaIdade;
        this.saldo = novoSaldo;

    }

    public String getNome() {
        return this.nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public int getIdade() {
        return this.idade;
    }

    public double getSaldo() {
        return this.saldo;
    }




    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }




    public void comprarServico(Servico servicoEscolhido) {
        if (this.saldo > servicoEscolhido.preco && servicoEscolhido.estaAtivo) {
            this.saldo = this.saldo - servicoEscolhido.preco;

            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Erro: saldo insuficiente ou servico inativo.");
        }
    }
}


class Servico {

    private String titulo;
    private String descricao;
    double preco;
    boolean estaAtivo;

    public Servico(String titulo, String descricao, double preco, boolean estaAtivo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.estaAtivo = estaAtivo;
    }

    public double getPreco() {
        return preco;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }


    public boolean isEstaAtivo() {
        return estaAtivo;
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

class ServicoDigital extends Servico {
    String linkDownload;

    public ServicoDigital(String titulo, String descricao, Double preco, Boolean estaAtivo, String linkDownload) {
        super(titulo, descricao, preco, estaAtivo);
        this.linkDownload = linkDownload;
    }
}

class Vendedor extends Pessoa {
    private Double taxaComissao;
    private int telefone;

    public Vendedor(String nome, String morada, int idade, Double saldo, Double taxaComissao, int telefone) {
        super(nome, morada, idade, saldo);
        this.taxaComissao = taxaComissao;
        this.telefone = telefone;

    }
    public int getTelefone() {
        return this.telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    public double getTaxaComissao() {
        return this.taxaComissao;
    }

}