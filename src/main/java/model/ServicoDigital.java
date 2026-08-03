package model;

public class ServicoDigital extends Servico {
    String linkDownload;

    public ServicoDigital(String titulo, String descricao, Double preco, Boolean estaAtivo, String linkDownload) {
        super(titulo, descricao, preco, estaAtivo);
        this.linkDownload = linkDownload;
    }

    public String getLinkDownload() {
        return linkDownload;
    }
}