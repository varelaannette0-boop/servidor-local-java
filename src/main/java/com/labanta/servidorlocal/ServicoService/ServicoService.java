package com.labanta.servidorlocal.ServicoService;

import com.labanta.servidorlocal.exception.ServicoNaoEncontradoException;
import com.labanta.servidorlocal.model.ServicoModel;
import com.labanta.servidorlocal.repository.ServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository repositorio;

    public ServicoService(ServicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Page<ServicoModel> listarTodos(Pageable paginavel) {
        return repositorio.findAll(paginavel);
    }

    public List<ServicoModel> aplicarDescontosEmAtivos(Double percentagem) {

        // Validar a percentagem
        if (percentagem < 0 || percentagem > 100) {
            throw new IllegalArgumentException("Desconto inválido.");
        }

        List<ServicoModel> servicosAtivos = this.repositorio.findByEstaAtivoTrue();

        for (ServicoModel servicos : servicosAtivos) {
            if (servicos.getPreco() >= 1000) {

                double precoComDesconto = servicos.getPreco()
                        - (servicos.getPreco() * percentagem / 100);

                servicos.setprecoComDesconto(precoComDesconto);
            }
        }

        this.repositorio.saveAll(servicosAtivos);

        return servicosAtivos;
    }

    public ServicoModel buscarServicoPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new ServicoNaoEncontradoException(
                                "O serviço com o ID " + id + " não existe no catálogo."
                        )
                );
    }

    public List<ServicoModel> pesquisarServicos(String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    public ServicoModel criarServico(ServicoModel novoServico) {
        return repositorio.save(novoServico);
    }
}