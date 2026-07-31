package model;

import java.util.ArrayList;
import java.util.List;


public class Pessoa {

    private String nome;
    private String morada;
    private int idade;
    private Double saldo;

    private List<Servico> servicosComprados;


    public Pessoa(String novoNome, String novaMorada, int novaIdade, Double novoSaldo) {
        this.nome = novoNome;
        this.morada = novaMorada;
        this.idade = novaIdade;
        this.saldo = novoSaldo;
        this.servicosComprados = new ArrayList<>();

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

    public List<Servico> getServicosComprados() {
        return servicosComprados;
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






    public void comprarServico(Servico servicoEscolhido) throws Exception {

        if (this.saldo < servicoEscolhido.getPreco()) {

            throw new Exception("Operação Recusada: Saldo Insuficiente.");
        }

        if (!servicoEscolhido.getEstaAtivo()) {
            throw new Exception("Operação Recusada: O serviço está inativo.");
        }


        this.saldo = this.saldo - servicoEscolhido.getPreco();
        this.servicosComprados.add(servicoEscolhido);
        System.out.println("Compra concluída com sucesso!");
    }


    public void mostrarHistorico() {
        System.out.println("--- Histórico de Compras ---");

        for (Servico s : this.servicosComprados) {
            System.out.println("- " + s.getTitulo());
        }
    }
}
