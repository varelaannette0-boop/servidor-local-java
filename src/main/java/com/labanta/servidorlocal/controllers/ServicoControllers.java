package com.labanta.servidorlocal.controllers;

import com.labanta.servidorlocal.ServicoService.EmailService;
import com.labanta.servidorlocal.ServicoService.ExchangeService;
import com.labanta.servidorlocal.ServicoService.FileStorageService;
import com.labanta.servidorlocal.ServicoService.ServicoService;
import com.labanta.servidorlocal.dto.ServicoResponseDTO;
import com.labanta.servidorlocal.model.ServicoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/servicos")
@SecurityRequirement(name = "BearerAuth")
public class ServicoControllers {

    private final ServicoService servicoService;
    private final ExchangeService exchangeService;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;

    public ServicoControllers(
            ServicoService servicoService,
            ExchangeService exchangeService,
            EmailService emailService,
            FileStorageService fileStorageService) {

        this.servicoService = servicoService;
        this.exchangeService = exchangeService;
        this.emailService = emailService;
        this.fileStorageService = fileStorageService;
    }

    // =========================================================
    // GET - LISTAR TODOS OS SERVIÇOS COM PAGINAÇÃO
    // =========================================================

    @Operation(
            summary = "Listar todos os serviços",
            description = "Rota para listar todos os serviços existentes na plataforma com paginação"
    )
    @GetMapping
    public Page<ServicoModel> listarTodos(
            @ParameterObject
            @PageableDefault(
                    size = 10,
                    sort = "titulo",
                    direction = Sort.Direction.ASC
            )
            Pageable paginavel) {

        return servicoService.listarTodos(paginavel);
    }

    // =========================================================
    // POST - CRIAR NOVO SERVIÇO
    // =========================================================

    @Operation(
            summary = "Criar um novo serviço",
            description = "Rota para criar um novo serviço"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping
    public ServicoModel criarNovo(
            @RequestBody ServicoModel novoServico) {

        return servicoService.criarServico(novoServico);
    }

    // =========================================================
    // POST - APLICAR DESCONTO
    // =========================================================

    @Operation(
            summary = "Aplicar desconto",
            description = "Aplica uma percentagem de desconto aos serviços ativos"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/aplicar-desconto")
    public List<ServicoResponseDTO> aplicarDesconto(
            @RequestParam Double percentagem) {

        List<ServicoModel> lista =
                servicoService.aplicarDescontosEmAtivos(percentagem);

        List<ServicoResponseDTO> resposta =
                new ArrayList<>();

        for (ServicoModel servico : lista) {

            ServicoResponseDTO dto =
                    new ServicoResponseDTO(
                            servico.getTitulo(),
                            servico.getPrecoComDesconto()
                    );

            resposta.add(dto);
        }

        return resposta;
    }

    // =========================================================
    // GET - TESTE DA API
    // =========================================================

    @Operation(
            summary = "Teste da API",
            description = "Endpoint simples para verificar se a API está funcionando"
    )
    @GetMapping("/teste")
    public String olaMundo() {

        return "Olá, Mundo!";
    }

    // =========================================================
    // GET - PESQUISAR SERVIÇOS
    // =========================================================

    @Operation(
            summary = "Pesquisar serviços",
            description = "Pesquisa serviços pelo título"
    )
    @GetMapping("/pesquisar")
    public List<ServicoModel> pesquisar(
            @RequestParam String titulo) {

        return servicoService.pesquisarServicos(titulo);
    }

    // =========================================================
    // GET - BUSCAR SERVIÇO POR ID
    // =========================================================

    @Operation(
            summary = "Buscar serviço por ID",
            description = "Retorna os dados de um serviço específico através do seu ID"
    )
    @GetMapping("/{id}")
    public ServicoModel buscarServicoPorId(
            @PathVariable Long id) {

        return servicoService.buscarServicoPorId(id);
    }

    // =========================================================
    // POST - SOLICITAR ORÇAMENTO
    // =========================================================

    @Operation(
            summary = "Solicitar orçamento",
            description = "Calcula o preço do serviço na moeda indicada e envia o orçamento por email"
    )
    @PostMapping("/{id}/orcamento")
    public String pedirOrcamento(
            @PathVariable Long id,
            @RequestParam String emailDestino,
            @RequestParam(defaultValue = "CVE") String moeda) {

        ServicoModel servico =
                servicoService.buscarServicoPorId(id);

        Double precoConvertido =
                exchangeService.converterPreco(
                        servico.getPreco(),
                        moeda
                );

        emailService.enviarEmailOrcamento(
                emailDestino,
                servico.getTitulo(),
                precoConvertido,
                moeda
        );

        return "Orçamento calculado e enviado com sucesso para "
                + emailDestino + "!";
    }

    // =========================================================
    // POST - UPLOAD DA IMAGEM DE CAPA
    // =========================================================

    @Operation(
            summary = "Carregar capa do serviço",
            description = "Rota para carregar uma imagem de capa para um serviço através do ID"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping(
            value = "/{id}/upload-capa",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadfile(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        // Procurar o serviço
        ServicoModel servico =
                servicoService.buscarServicoPorId(id);

        // Guardar a imagem
        String fileUpload =
                fileStorageService.storeImage(file);

        // Guardar o nome da imagem
        servico.setImagemCapa(fileUpload);

        // Atualizar o serviço
        servicoService.criarServico(servico);

        return ResponseEntity.ok(
                "Imagem carregada com sucesso: "
                        + fileUpload
        );
    }
}