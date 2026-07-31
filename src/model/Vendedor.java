package model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {

    private Double taxaComissao;
    private int telefone;
    private List servicosAVenda;

    public Vendedor(String nome, String morada, int idade, Double saldo, Double taxaComissao, int telefone) {
        super(nome, morada, idade, saldo);
        this.taxaComissao = taxaComissao;
        this.telefone = telefone;
        this.servicosAVenda = new ArrayList<>();
    }

    public double getTaxaComissao() {
        return this.taxaComissao;
    }
    public int getTelefone() {
        return this.telefone;
    }
    public List getServicosAVenda() {
        return this.servicosAVenda;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }



    public void publicarServico(Servico novoServico) throws Exception {

        if (novoServico.getPreco() <= 0) {
            throw new Exception("O preço tem de ser superior a zero!");
        } else {
            servicosAVenda.add(novoServico);
            System.out.println("Serviço publicado com sucesso!");
        }
    }
}
