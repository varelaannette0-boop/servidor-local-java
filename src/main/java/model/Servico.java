package model;

import utils.Formatador;

public class Servico {

    private String titulo;
    private String descricao;
    private double preco;
    private boolean estaAtivo;

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


    public boolean getEstaAtivo() {
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