package com.labanta.servidorlocal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Double preco;
    private boolean estaAtivo;
    private double precoComDesconto;
    private String imagemCapa;

    public ServicoModel() {};
    public ServicoModel( String titulo, String descricao, Double preco, boolean estaAtivo, double precoComDesconto, String imagemCapa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.estaAtivo = estaAtivo;
        this.precoComDesconto = precoComDesconto;
        this.imagemCapa = imagemCapa;

    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(Double preco) {this.preco = preco;}

    public void setEstaAtivo(boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }

    public void setprecoComDesconto(double precoComDesconto) {this.precoComDesconto = precoComDesconto;}

    public void setImagemCapa(String imagemCapa) {this.imagemCapa = imagemCapa;}

    public String getTitulo() {return this.titulo;}

    public String getDescricao() {
        return this.descricao;
    }

    public Double getPreco() {
        return this.preco;
    }

    public boolean getEstaAtivo() {
        return this.estaAtivo;
    }

    public double getPrecoComDesconto() {
        return this.precoComDesconto;
    }

    public String getImagemCapa() {return imagemCapa;}


    public void aplicarDesconto(double percentagem) {
        double valorDesconto = (this.preco * percentagem / 100);

        this.preco = this.preco - valorDesconto;

        System.out.println("Desconto aplicado com sucesso");
        System.out.println("valor final" + this.preco);
    }

    public void verificarDisponibilidade() {
        if (this.estaAtivo) {
            System.out.println("Servico " + this.titulo + " esta disponivel");
        } else {
            System.out.println("Servico " + this.titulo + " nao esta disponivel");
        }
    }



}