package com.labanta.servidorlocal.repository;

import com.labanta.servidorlocal.model.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<ServicoModel, Long> {

    List<ServicoModel> findByEstaAtivoTrue();

    List<ServicoModel> findByTituloContainingIgnoreCase(String titulo);
}